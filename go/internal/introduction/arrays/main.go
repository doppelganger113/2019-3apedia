package arrays

import "errors"

func AddNumbers(sliceArray []int, numbers ...int) []int {
	return append(sliceArray, numbers...)
}

func RemoveNumber(slice []int, index int) ([]int, error) {
	if index >= len(slice) {
		return nil, errors.New("index out of bounds")
	}
	return append(slice[:index], slice[index+1:]...), nil
}
