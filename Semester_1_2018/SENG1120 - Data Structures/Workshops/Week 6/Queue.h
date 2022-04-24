#ifndef SAM_QUEUE
#define SAM_QUEUE
#include "LinkedList.h"

using namespace std;

class Queue
{
  public:
    typedef LinkedList::value_type value_type;
    Queue();
    ~Queue();
    bool is_empty();
    void enqueue(value_type);
    value_type dequeue();
    value_type front();
    int size();

  private:
    LinkedList data;
    int used;
};

#endif
