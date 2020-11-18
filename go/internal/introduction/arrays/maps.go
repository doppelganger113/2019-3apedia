package arrays

import "fmt"

func handleMaps() {
	hashMap := make(map[string]int)
	hashMap["1"] = 1
	hashMap["2"] = 0

	val, ptr := hashMap["1"]
	fmt.Println(val, ptr)

	val, ptr = hashMap["2"]
	fmt.Println(val, ptr)
}
