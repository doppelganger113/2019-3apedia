package tennis

import (
	"fmt"
	"math/rand"
	"sync"
)

func Play() {
	var wg sync.WaitGroup
	court := make(chan int)
	go player("Nadal", court, &wg)
	go player("Djokovic", court, &wg)
	court <- 1
	fmt.Println("Game finished!")
}

func player(playerName string, court chan int, doneWg *sync.WaitGroup) {
	defer doneWg.Done()
	for {
		ball, ok := <-court
		if !ok {
			fmt.Printf("Player %s won\n", playerName)
			return
		}

		randomNum := rand.Intn(100)
		if randomNum%13 == 0 {
			fmt.Printf("Player %s missed\n", playerName)
			close(court)
			return
		}

		fmt.Printf("Player %s hit %d\n", playerName, ball)
		ball++

		court <- ball
	}
}
