package testing

import "testing"

func TestCreateHello(t *testing.T) {
	value := CreateHello("John")
	if value != "Hello John" {
		t.Errorf("Failed for value %s", value)
	}
}

func BenchmarkCreateHello(b *testing.B) {
	for i := 0; i < b.N; i++ {
		CreateHello("John")
	}
}
