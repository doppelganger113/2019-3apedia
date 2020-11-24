package movies

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"
)

type MovieData struct {
	Title                string  `json:"Title"`
	UsGross              int     `json:"US Gross"`
	WorldwideGross       int     `json:"Worldwide Gross"`
	USDVDSales           int     `json:"US DVD Sales"`
	ProductionBudget     int     `json:"Production Budget"`
	ReleaseDate          string  `json:"Release Date"`
	MPAARating           string  `json:"MPAA Rating"`
	RunningTimeMin       int     `json:"Running Time min"`
	Distributor          string  `json:"Distributor"`
	Source               string  `json:"Source"`
	MajorGenre           string  `json:"Major Genre"`
	CreativeType         string  `json:"Creative Type"`
	Director             string  `json:"Director"`
	RottenTomatoesRating int     `json:"Rotten Tomatoes Rating"`
	IMDBRating           float64 `json:"IMDB Rating"`
	IMDBVotes            int     `json:"IMDB Votes"`
}

func check(err error) {
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	file, err := os.Open("data/movies.json")
	check(err)
	defer file.Close()

	data, err := ioutil.ReadAll(file)
	check(err)

	var movies []MovieData
	err = json.Unmarshal(data, &movies)
	check(err)

	fmt.Println(movies[0].IMDBRating)

	best := make([]MovieData, 0)
	good := make([]MovieData, 0)
	ok := make([]MovieData, 0)
	bad := make([]MovieData, 0)
	terrible := make([]MovieData, 0)

	for _, movie := range movies {
		switch {
		case movie.IMDBRating >= 8:
			best = append(best, movie)
		case movie.IMDBRating >= 6:
			good = append(good, movie)
		case movie.IMDBRating >= 4:
			ok = append(ok, movie)
		case movie.IMDBRating >= 2:
			bad = append(bad, movie)
		case movie.IMDBRating >= 0:
			terrible = append(terrible, movie)
		}

	}
	fmt.Printf(
		"Great: %d Good: %d Ok %d Bad: %d Terrible %d",
		len(best), len(good), len(ok), len(bad), len(terrible),
	)
}
