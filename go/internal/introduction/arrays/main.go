package arrays

import "fmt"

func addNumbers(sliceArray *[]int, numbers ...int) []int {
	return append(*sliceArray, numbers...)
}

func main() {
	numbers := make([]int, 2, 10)
	newNumbers := append(numbers, 6, 7)
	fmt.Println(numbers)
	fmt.Println(newNumbers)

	numbers[1] = 10
	fmt.Println(".........")

	fmt.Println(numbers)
	fmt.Println(newNumbers)
}
