package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"sync"
)

// Task
// Use HackerNewsAPI to retrieve list of items based on ID
// You will need to perform HTTP requests concurrently through goroutines
// Create a function that will accept a slice of int ids and return list of items

// https://hacker-news.firebaseio.com/v0/item/8863.json

// resp, err := http.Get("http://example.com/")
// if err != nil {
//   // handle error
// }
// defer resp.Body.Close()
// body, err := ioutil.ReadAll(resp.Body)

type HackerNewsApi struct {
	Domain string
}

func NewHackerNewsApi() *HackerNewsApi {
	return &HackerNewsApi{
		"https://hacker-news.firebaseio.com",
	}
}

func (api HackerNewsApi) fetchItem(id int) ([]byte, error) {
	resp, err := http.Get(
		fmt.Sprintf(
			"%s/v0/item/%d.json",
			api.Domain,
			id,
		),
	)
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	data, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return nil, err
	}

	return data, nil
}

func (api HackerNewsApi) fetchItems(ids ...int) ([][]byte, error) {
	var wg sync.WaitGroup
	var mtx sync.Mutex
	items := make([][]byte, 0)
	var e error

	for id := range ids {
		go func(i int) {
			wg.Add(1)
			defer wg.Done()
			item, err := api.fetchItem(i)
			if err != nil {
				e = err
				return
			}
			mtx.Lock()
			defer mtx.Unlock()
			items = append(items, item)
		}(id)
	}
	wg.Wait()

	return items, e
}

func main() {
	api := NewHackerNewsApi()
	items, err := api.fetchItems(
		8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005, 9671, 8940,
	)
	if err != nil {
		log.Fatal(err)
	}

	for _, item := range items {
		fmt.Println(string(item))
	}
	fmt.Println(len(items))

	var result1 []byte
	var result2 []byte
	go func() {
		result1, err = api.fetchItem(8952)
	}()

	go func() {
		result2, err = api.fetchItem(8952)
	}()
}
