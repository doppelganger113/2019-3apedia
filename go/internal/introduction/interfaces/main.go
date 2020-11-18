package interfaces

import "fmt"

type Reader interface {
	Read() string
}

type Person struct {
	Name string
}

func (person Person) Read() string {
	return person.Name
}

func printer(reader Reader) {
	fmt.Println(reader.Read())
}

func main() {
	person := Person{"John"}
	printer(person)
}
