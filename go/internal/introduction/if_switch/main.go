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

	whatAmI := func(i interface{}) {
		switch t := i.(type) {
		case bool:
			fmt.Println("I'm a bool")
		case int:
			fmt.Println("I'm an int")
		default:
			fmt.Printf("Don't know type %T\n", t)
		}
	}
	whatAmI(true)
	whatAmI(1)
	whatAmI("hey")
}
