package if_switch

import (
	"errors"
	"fmt"
)

func doSomething() (string, error) {
	return "", errors.New("MyError")
}

func handleSwitch(value string) {
	switch value {
	case "world":
		fmt.Println("Got world")
	case "hello":
		fmt.Println("Got hello")
	default:
		fmt.Println("Got unknown " + value)
	}

	fmt.Println("Done.")
}

func main() {
	if val, err := doSomething(); err == nil {
		fmt.Println("Got something: " + val)
	}

	handleSwitch("world")
	handleSwitch("hello")
	handleSwitch("whatever")
}
