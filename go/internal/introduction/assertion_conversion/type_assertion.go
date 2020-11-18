package assertion_conversion

import "fmt"

type Dog struct {
	Name string
}

type Address struct {
	Value string
}

type Book struct {
	Name string
}

func handleType(object interface{}) string {
	// value, ok := object.(Dog)
	switch object.(type) {
	case Dog:
		return "Dog"
	case Address:
		return "Address"
	default:
		return "Unknown"
	}
}

func handleAssertion() {
	fmt.Println(handleType(Book{}))
	fmt.Println(handleType(Dog{}))
	fmt.Println(handleType(nil))
}
