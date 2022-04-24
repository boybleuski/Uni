/*
    BSTree.h

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 8/6/2018
    Description: Header file for the BSTree class.
*/
#ifndef SAM_BSTREE
#define SAM_BSTREE
#include <cstdlib>
#include <string>
#include <iostream>
#include "BTNode.h"

using namespace std;

class BSTree
{
  public:
    //constructors
    BSTree();

    //destructors
    ~BSTree();

    //queriers
    BTNode* get_root() const;
    //Precondition: None
    //Postcondition: Return the root node

    //mutators
    void add(int);
    //Precondition: Receives data to be inserted into a node
    //Postcondition: If the tree is empty, creates the root node.  Otherwise,
    //sends data to the insert() function
    void insert(BTNode*, int);
    //Precondition: Receives data to be inserted into a node and a pointer to The
    //target node.
    //Postcondition: Adds a new node to the tree
    string toString();
    //Precondition: None
    //Postcondition: Returns the root node to start outputting the tree
    string toString(BTNode*, int);
    //Precondition: Receives the data to be output and the level of the tree
    //Postcondition: Returns the structure of the tree to be output
    void remove(int);
    //Precondition: Receives the data to be removed from the tree
    //Postcondition: Determines the node to be removed based on the data
    void removeFromTree(BTNode*);
    //Precondition: Receives the node to be removed
    //Postcondition: Removes the node and repairs the pointers accordingly

  private:
    BTNode* root;
    BTNode* current;
};

ostream& operator<< (ostream&, BSTree&);
#endif
