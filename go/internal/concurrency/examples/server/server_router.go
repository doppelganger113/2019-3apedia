package server

import (
	"fmt"
	"github.com/julienschmidt/httprouter"
	"log"
	"net/http"
)

func Start() {
	router := httprouter.New()
	router.GET("/", handleHome)

	log.Println("Server started.")
	if err := http.ListenAndServe(":8080", router); err != nil {
		log.Fatalln("Server closed abruptly: ", err)
	}
}

func handleHome(writer http.ResponseWriter, request *http.Request, params httprouter.Params) {
	fmt.Fprint(writer, "Hello from GO server\n")
}
