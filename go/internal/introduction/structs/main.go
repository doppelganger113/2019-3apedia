package structs

import "fmt"

type User struct {
	Name string
}

func (user *User) getName() string {
	return fmt.Sprintf("My name is %s", user.Name)
}

type Vertex struct {
	User
	X int
	Y int
}

func (v *Vertex) getName() string {
	return fmt.Sprintf("X: %d Y: %d", v.X, v.Y)
}

func update(v Vertex) Vertex {
	v.X = 5
	v.Y = v.Y + 2

	return v
}
