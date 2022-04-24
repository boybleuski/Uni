#ifndef SAM_ITEM
#define SAM_ITEM

#include <cstdlib>
#include <string>
#include <iostream>

using namespace std;

template <class object>
class Item
{
  public:
    Item(object);
    ~Item();
    object get_value();
    bool operator == (const Item&);
    bool operator > (const Item&);
    bool operator < (const Item&);
    bool operator != (const Item&);

  private:
    object value;
};

#include "Item.template"
#endif
