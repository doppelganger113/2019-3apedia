package main

import (
	"fmt"
	"strings"
)

type Person struct {
	Name             string
	Age              int
	hasDriverLicence bool
}

func (p Person) toString() string {
	return fmt.Sprintf("Name: %s Age: %d", p.Name, p.Age)
}

func alterPerson(p Person) {
	p.Name = strings.ToUpper(p.Name)
}

func logable(fn func()) func() {
	return func() {
		fmt.Println("Started")
		fn()
		fmt.Println("Completed")
	}
}

func sayHello() {
	fmt.Println("Hello")
}

// [0] [0] [0] [0] [3] [0] [1]

func main() {
	p1 := Person{"John", 25, true}
	//p2 := Person{"Nick", 20, true}
	fmt.Println(p1.toString())
	//persons := []Person{p1, p2}
}
