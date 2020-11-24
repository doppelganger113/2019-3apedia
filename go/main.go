package main

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
	IMDBRating           float64 `json:"IMDBRating"`
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

	fmt.Println(movies[0])
}
