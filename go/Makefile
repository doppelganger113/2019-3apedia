SHELL=/bin/bash
PROJECTNAME=app

GOBASE=$(shell pwd)
GOBIN=$(GOBASE)

default:
	go build -i -v -o $(GOBIN)/bin/$(PROJECTNAME) ./cmd/$(PROJECTNAME)/main.go || exit
install:
	go mod download
start:
	go build -i -v -o $(GOBIN)/bin/$(PROJECTNAME) ./cmd/$(PROJECTNAME)/main.go || exit
	$(GOBIN)/bin/$(PROJECTNAME)
run:
	go run cmd/app/main.go
format:
	go fmt ./...
test:
	go test -race ./...
compile:
	# 32-Bit Systems
	# FreeBDS
	GOOS=freebsd GOARCH=386 go build -i -v -o $(GOBIN)/bin/main-freebsd-386 ./cmd/$(PROJECTNAME)/main.go
	# MacOS
	GOOS=darwin GOARCH=386 go build -i -v -o $(GOBIN)/bin/main-darwin-386 ./cmd/$(PROJECTNAME)/main.go
	# Linux
	GOOS=linux GOARCH=386 go build -i -v -o $(GOBIN)/bin/main-linux-386 ./cmd/$(PROJECTNAME)/main.go
	# Windows
	GOOS=windows GOARCH=386 go build -i -v -o $(GOBIN)/bin/main-windows-386 ./cmd/$(PROJECTNAME)/main.go
    # 64-Bit
	# FreeBDS
	GOOS=freebsd GOARCH=amd64 go build -i -v -o $(GOBIN)/bin/main-freebsd-amd64 ./cmd/$(PROJECTNAME)/main.go
	# MacOS
	GOOS=darwin GOARCH=amd64 go build -i -v -o $(GOBIN)/bin/main-darwin-amd64 ./cmd/$(PROJECTNAME)/main.go
	# Linux
	GOOS=linux GOARCH=amd64 go build -i -v -o $(GOBIN)/bin/main-linux-amd64 ./cmd/$(PROJECTNAME)/main.go
	# Windows
	GOOS=windows GOARCH=amd64 go build -i -v -o $(GOBIN)/bin/main-windows-amd64 ./cmd/$(PROJECTNAME)/main.go
