// lab11
package main

import (
	"fmt"
)

func main() {
	fmt.Println("start")
	fido := dog{"Fido", "Poodle", false}
	fido.rename("Cocotte")
	fmt.Println(fido.name)
}

func (d *dog) rename(newName string) {
	d.name = newName
	return
}

type dog struct {
	name   string
	race   string
	female bool
}
