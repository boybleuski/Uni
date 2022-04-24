// Queue.h
// Author: Sam Dolbel
// Student No.: c3130069
// Course: SENG1120
// Last modified: 12/5/2018

#include <cstdlib>
#include <iostream>
#include <string>
#include "LinkedList.h"
#include "Node.h"

using namespace std;

template <class value_type>
class Queue
{
  public:
    //constructor
    Queue();

    //destructor
    ~Queue();

    //queriers
    int size() const;
    // Precondition: None
    // Postcondition: Returns the number of entries in the queue
    bool is_empty() const;
    // Precondition: None
    // Postcondition: Returns true is the queue is empty, false if not
    double average();
    // Precondition: None
    // Postcondition: Returns the average of all the entries in the queue
    value_type front();
    // Precondition: None
    // Postcondition: Returns the value of the object in the front of the queue

    //mutators
    void enqueue(const value_type& obj);
    // Precondition: Receives data to add to the queue
    // Postcondition: Adds a new object to the back of the queue
    value_type dequeue();
    // Precondition: None
    // Postcondition: Returns the value of the object at the front of the queue,
    // and removes the object from the queue

  private:
    LinkedList<value_type> data;
    int used;
};

#include "Queue.template"
