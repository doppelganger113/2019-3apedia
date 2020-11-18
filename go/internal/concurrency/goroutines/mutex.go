package goroutines

import (
	"encoding/json"
	"fmt"
	"github.com/fatih/color"
	"io/ioutil"
	"log"
	"net/http"
	"sync"
)

func Run() {
	var wg sync.WaitGroup
	var mtx sync.Mutex
	counter := 0

	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func(val int) {
			mtx.Lock()
			defer mtx.Unlock()
			defer wg.Done()
			counter = counter + val
		}(i)
	}
	wg.Wait()

	fmt.Println(counter)
}

func RunHackerNews() {
	api := NewHackerNewsApi()
	numGoroutines := 10

	executeConcurrently(numGoroutines, func() {
		item, err := api.fetchItemCached(8863)
		if err != nil {
			log.Fatal("Error fetching item", err)
		}
		fmt.Println("Got item", item)
	})
}

func executeConcurrently(workers int, fn func()) {
	var wg sync.WaitGroup

	for i := 0; i < workers; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			fn()
		}()
	}

	wg.Wait()
}

type HackerNewsApi struct {
	Mtx    sync.Mutex
	Cached *Story
	domain string
}

func NewHackerNewsApi() *HackerNewsApi {
	return &HackerNewsApi{
		domain: "https://hacker-news.firebaseio.com",
	}
}

type Story struct {
	Id    int    `json:"id"`
	Title string `json:"title"`
	Type  string `json:"type"`
	By    string `json:"by"`
	Url   string `json:"url"`
}

func (api *HackerNewsApi) fetchItem(id int) (*Story, error) {
	fmt.Println("[API]: Making a GET request")
	res, err := http.Get(
		fmt.Sprintf(
			"%s/v0/item/%d.json",
			api.domain,
			id,
		),
	)
	if err != nil {
		return nil, err
	}
	defer res.Body.Close()

	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		return nil, err
	}
	var story Story
	err = json.Unmarshal(body, &story)
	if err != nil {
		return nil, err
	}

	return &story, nil
}

func (api *HackerNewsApi) fetchItemCached(id int) (*Story, error) {
	api.Mtx.Lock()
	defer api.Mtx.Unlock()
	if cached := api.Cached; cached != nil {
		color.Green("[Cache hit]: returning cached")
		return cached, nil
	}
	color.Yellow("[Cache miss]: retrieving")
	story, err := api.fetchItem(id)
	if err != nil {
		return nil, err
	}

	color.Cyan("[Cache]: setting cached")
	api.Cached = story

	return story, nil
}
