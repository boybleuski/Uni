CC=g++
CFLAGS=-c -Wall
LDFLAGS=
SOURCES=TreeHashTableDemo.cpp BTNode.cpp BSTree.cpp HTable.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=assignment3

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
