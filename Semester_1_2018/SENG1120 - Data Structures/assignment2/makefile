CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=GradesDemo.cpp Node.h LinkedList.h Queue.h Stack.h
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=assignment2

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -g -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
