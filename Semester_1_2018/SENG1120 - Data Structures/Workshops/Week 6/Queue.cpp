#include <iostream>
#include <string>
#include <cstdlib>
#include "Queue.h"

Queue::Queue()
{
  used = 0;
}

Queue::~Queue()
{

}

bool Queue::is_empty()
{
  if (used==0)
    return true;
  else
    return false;
}

void Queue::enqueue(value_type entry)
{
  used++;
  data.add_to_tail(entry);
}

string Queue::dequeue()
{
  if (is_empty()==false)
  {
    used--;
    return data.remove_from_head();
  }
  else
  {
    return "Empty.  Like your heart.";
  }
}

string Queue::front()
{
  data.start();
  return data.get_current();
}

int Queue::size()
{
  return used;
}
