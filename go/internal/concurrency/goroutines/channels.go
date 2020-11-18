package goroutines

import (
	"fmt"
	"github.com/fatih/color"
	"sync"
	"time"
)

func emit(emitter chan<- int) {
	for i := 0; i < 50; i++ {
		time.Sleep(time.Millisecond * 100)
		emitter <- i
	}
	close(emitter)
}

func RunChannels() {
	emitter := make(chan int)

	go emit(emitter)

	var wg sync.WaitGroup
	wg.Add(2)
	go func() {
		defer wg.Done()
		isOk := true

		for isOk {
			val, ok := <-emitter
			isOk = ok
			fmt.Println("Working...")
			time.Sleep(3 * time.Second)
			color.Cyan("Value: ", val)
		}
	}()

	go func() {
		defer wg.Done()
		isOk := true

		for isOk {
			val, ok := <-emitter
			isOk = ok
			color.Green("Value: ", val)
		}
	}()

	wg.Wait()
}
