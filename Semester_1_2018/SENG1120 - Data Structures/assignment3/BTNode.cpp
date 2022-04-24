/*
    BTNode.cpp

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 7/6/2018
    Description: Defines the BTNode class, which stores the values of each node
    in the BSTree class, as well as pointers to relevant nodes.
*/

#include <cstdlib>
#include "BTNode.h"

using namespace std;

BTNode::BTNode()
{
  value = 0;
  left = NULL;
  right = NULL;
  parent = NULL;
}

BTNode::BTNode(int newValue, BTNode* newLeft, BTNode* newRight, BTNode* newParent)
{
  value = newValue;
  left = newLeft;
  right = newRight;
  parent = newParent;
}

BTNode::~BTNode()
{
  value = 0;
  left = NULL;
  right = NULL;
  parent = NULL;
}

int BTNode::get_value() const
{
  return value;
}

BTNode* BTNode::get_left() const
{
  return left;
}

BTNode* BTNode::get_right() const
{
  return right;
}

BTNode* BTNode::get_parent() const
{
  return parent;
}

void BTNode::set_value(int newValue)
{
  value = newValue;
}

void BTNode::set_left(BTNode* newLeft)
{
  left = newLeft;
}

void BTNode::set_right(BTNode* newRight)
{
  right = newRight;
}

void BTNode::set_parent(BTNode* newParent)
{
  parent = newParent;
}
