/*
    BSTree.cpp

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 8/6/2018
    Description: Defines the BSTree class, a binary search tree.
*/

#include <cstdlib>
#include <string>
#include <iostream>
#include <sstream>
#include "BSTree.h"

using namespace std;

BSTree::BSTree()
{
  root = NULL;
  current = NULL;
}

BSTree::~BSTree()
{
  root = NULL;
  current = NULL;
}

BTNode* BSTree::get_root() const
{
  return root;
}

void BSTree::add(int newData) // adds a new BTNode to the BSTree
{
  if (root == NULL)
    root = new BTNode(newData);
  else
    insert(root, newData);
}

void BSTree::insert(BTNode* node, int newData) // determines where to insert the BTNode
{
  current = node;

  if (newData == current->get_value()) // if a BTNode with the same data already exists, overwrites the original
    current->set_value(newData);
  else if (newData > current->get_value()) // adds BTNode to the right of the parent node
  {
    if(current->get_right() == NULL)
    {
       BTNode* newNode = new BTNode(newData);
       current->set_right(newNode);
       newNode->set_parent(current);
    }
    else
    {
      current = current->get_right();
      insert(current, newData);
    }
  }
  else // adds BTNode to the left of the parent node
  {
    if(current->get_left() == NULL)
    {
      BTNode* newNode = new BTNode(newData);
      current->set_left(newNode);
      newNode->set_parent(current);
    }
    else
    {
      current = current->get_left();
      insert(current, newData);
    }
  }
}

string BSTree::toString() // prepares a BSTree to be output
{
  return toString(root, 0);
}

string BSTree::toString(BTNode* node, int level) // determines visual structure of output BSTree
{
  stringstream ss;
  string space = "";
  if(node != NULL)
  {
    ss << node->get_value();
    string str = ss.str(); // converts int to string to be more easily output
    space += toString(node->get_right(), level+1);
    for(int i=0; i<level; i++)
      space = space + "      ";
    space += str + "\n";
    space += toString(node->get_left(), level+1);
  }
  return space;
}

void BSTree::removeFromTree(BTNode* targetNode) // deletes a BTNode from the BSTree
{
  BTNode* temp;
  BTNode* behindCurrent = targetNode->get_parent();

  if(targetNode->get_left()==NULL && targetNode->get_right()==NULL) // for nodes with no children
  {
    if(behindCurrent->get_value() > targetNode->get_value())
      behindCurrent->set_left(NULL);
    else
      behindCurrent->set_right(NULL);
    temp = targetNode;
    delete temp;
  }

  else if (targetNode->get_left()==NULL) // for nodes with only a right-side child
  {
    if(behindCurrent->get_value() > targetNode->get_value())
    {
      behindCurrent->set_left(targetNode->get_right());
      targetNode->get_right()->set_parent(behindCurrent);
    }
    else
    {
      behindCurrent->set_right(targetNode->get_right());
      targetNode->get_right()->set_parent(behindCurrent);
    }
    temp = targetNode;
    delete temp;
  }

  else if (targetNode->get_right()==NULL) // for nodes with only a left-side child
  {
    if(behindCurrent->get_value() > targetNode->get_value())
    {
      behindCurrent->set_left(targetNode->get_left());
      targetNode->get_left()->set_parent(behindCurrent);
    }
    else
    {
      behindCurrent->set_right(targetNode->get_left());
      targetNode->get_left()->set_parent(behindCurrent);
    }
    temp = targetNode;
    delete temp;
  }

  else // for nodes with children on both sides
  {
    current = targetNode->get_left();
    behindCurrent = NULL;

    while(current->get_right()!=NULL)
    {
      behindCurrent = current;
      current = current->get_right();
    }

    targetNode->set_value(current->get_value());

    if(behindCurrent==NULL)
      targetNode->set_left(current->get_left());
    else
      behindCurrent->set_right(current->get_left());
    delete current;
  }
}

void BSTree::remove(int target) // locates a BTNode to be deleted
{
  current = root;
  BTNode* behindCurrent;
  bool found = false;

  if(root==NULL)
    cout << "The tree is empty.";
  else
  {
    do
    {
      if (current->get_value() == target) // if the current node contains the value
        found = true;
      else // continue searching for the node with the value
      {
        behindCurrent = current;
        if (current->get_value() > target)
          current = current->get_left();
        else
          current = current->get_right();
      }
    }
    while(current!=NULL && found==false);

  if (current==NULL) // if the value is not present in the hash table
    cout << "Item not found." << endl;
  else if (found)
  {
    if (current == root)
      removeFromTree(root); // for removing the root node
    else if (behindCurrent->get_value() > target)
      removeFromTree(behindCurrent->get_left()); // deletes the node to the left of the parent node
    else
      removeFromTree(behindCurrent->get_right()); // deletes the node to the right of the parent node
  }
    else
      cout << "Item not found." << endl;
  }
}

ostream& operator<< (ostream& out, BSTree& myTree)
{
  out << endl << myTree.toString() << endl;
  return out;
}
