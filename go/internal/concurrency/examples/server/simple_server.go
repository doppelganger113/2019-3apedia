package server

import (
	"encoding/json"
	"log"
	"net/http"
)

type Data struct {
	Value string `json:"value"`
}

type Handler = func(writer http.ResponseWriter, request *http.Request)
type JsonHandler = func(request *http.Request) []byte

func myMiddleware(next JsonHandler) Handler {
	return func(writer http.ResponseWriter, request *http.Request) {
		log.Println("[Middleware] Before")
		response := next(request)
		writer.Header().Set("Content-Type", "application/json")
		writer.Write(response)
		log.Println("[Middleware] After")
	}
}

func helloHandler(request *http.Request) []byte {
	stringified, _ := json.Marshal(&Data{Value: "John"})

	return stringified
}

func StartSimple() {
	http.HandleFunc("/", myMiddleware(helloHandler))

	log.Println("Server started.")
	if err := http.ListenAndServe(":8080", nil); err != nil {
		log.Fatal("Server ended abruptly", err)
	} else {
		log.Println("Server closed.")
	}
}
