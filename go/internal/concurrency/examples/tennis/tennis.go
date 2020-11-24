package tennis

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func Play() {
	var wg sync.WaitGroup
	court := make(chan int)

	wg.Add(2)
	go player("Djokovic", court, &wg)
	go player("Nadal", court, &wg)

	court <- 1
	wg.Wait()
	fmt.Println("Game finished.")
}

func player(name string, court chan int, done *sync.WaitGroup) {
	defer done.Done()
	for {
		ball, ok := <-court
		time.Sleep(200)
		if !ok {
			fmt.Printf("Player %s won!\n", name)
			return
		}

		randomNum := rand.Intn(100)
		if randomNum%13 == 0 {
			fmt.Printf("Player %s missed\n", name)
			close(court)
			return
		}

		fmt.Printf("Player %s hit the ball %d\n", name, ball)
		ball++

		court <- ball
	}
}
