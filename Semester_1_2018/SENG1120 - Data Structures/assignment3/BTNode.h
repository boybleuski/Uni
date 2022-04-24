/*
    BTNode.h

    Author: Sam Dolbel
    Class: SENG1120
    Last modified: 7/6/2018
    Description: Header file for the BTNode class.
*/

#ifndef SAM_BTNODE
#define SAM_BTNODE
#include <cstdlib>

using namespace std;

class BTNode
{
  public:
    //constructors
    BTNode();
    BTNode(int newValue=0, BTNode* newLeft=NULL, BTNode* newRight=NULL, BTNode* newParent=NULL);

    //destructors
    ~BTNode();

    //queriers
    int get_value() const;
    //Preconditon: None
    //Postcondition: Returns the value stored in the node
    BTNode* get_left() const;
    //Precondition: None
    //Postcondition: Returns the pointer to the left node
    BTNode* get_right() const;
    //Precondition: None
    //Postcondition: Returns the pointer to the right node
    BTNode* get_parent() const;
    //Precondition: None
    //Postcondition: Returns the pointer to the parent node

    //mutators
    void set_value(int);
    //Precondition: Receives the new value in the node
    //Postcondition: Updates the value stored in the node
    void set_left(BTNode*);
    //Precondition: Receives the new pointer to the left node
    //Postcondition: Updates the pointer to the left node
    void set_right(BTNode*);
    //Precondition: Receives the new pointer to the right node
    //Postcondition: Updates the pointer to the right node
    void set_parent(BTNode*);
    //Precondition: Receives the new pointer to the parent node
    //Postcondition: Updates the pointer to the parent node

  private:
    int value;
    BTNode* left;
    BTNode* right;
    BTNode* parent;
};

#endif
