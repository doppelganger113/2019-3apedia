package main

import (
	"fmt"
	"go-presentation/internal/introduction/arrays"
)

func main() {
	numbers := []int{1, 2, 3, 4}

	fmt.Println(
		arrays.AddNumbers(numbers, 5, 6, 7),
	)
}
