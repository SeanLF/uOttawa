// 6778524

package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	ch1 := make(chan int)
	ch2 := make(chan int)
	fmt.Println("Start main()")
	go readF2C("/Users/Sean/Dropbox/2ndYear/CSI2120-Paradigms/Python/multiples11.txt", ch1)
	go readF2C("/Users/Sean/Dropbox/2ndYear/CSI2120-Paradigms/Python/multiples12.txt", ch2)
	writeC2F("/Users/Sean/Dropbox/2ndYear/CSI2120-Paradigms/Go/mult11_12.txt", ch1, ch2)
}

func readF2C(fn string, ch chan int) {
	inF, errRead := os.Open(fn)
	if errRead != nil {
		fmt.Println("nil upon read")
	}
	scanner := bufio.NewScanner(inF)
	for scanner.Scan() {
		value, errorMessage := strconv.Atoi(scanner.Text())
		ch <- value
		if errorMessage != nil {
		}
	}
	ch <- -1
	inF.Close()

}

func writeC2F(fn string, ch1 chan int, ch2 chan int) {
	val1 := <-ch1
	val2 := <-ch2
	fo, err := os.Create(fn)
	if err != nil {
		return
	}
	for {
		if val1 > val2 && val1 != -1 && val2 != -1 {
			write := strconv.Itoa(val2) + "\n"
			val2 = <-ch2
			fo.WriteString(write)
		} else if val1 != -1 && val2 != -1 {
			write := strconv.Itoa(val1) + "\n"
			val1 = <-ch1
			fo.WriteString(write)
		}
		if val1 == -1 {
			for {
				if val2 != -1 {
					write := strconv.Itoa(val2) + "\n"
					val2 = <-ch2
					fo.WriteString(write)
				} else {
					break
				}
			}
			break
		}
		if val2 == -1 {
			for {
				if val1 != -1 {
					write := strconv.Itoa(val1) + "\n"
					val1 = <-ch1
					fo.WriteString(write)
				} else {
					break
				}
			}
			break
		}
	}
	fo.Close()
}
