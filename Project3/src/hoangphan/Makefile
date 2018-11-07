##Makefile for project 3
.SUFFIXES: .java .class

JCC = javac
JFLAGS = -g
JVM = java

all: Stack.class Queue.class Project3.class

Stack.class: Stack.java
	javac Stack.java

Queue.class: Queue.java
	javac Queue.java

Project3.class: Project3.java
	javac Project3.java

clean:
	rm *.class

run:
	java Project3 < test1.txt
