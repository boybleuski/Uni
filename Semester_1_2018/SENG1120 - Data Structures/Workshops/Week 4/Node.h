#ifndef SAM_NODE
#define SAM_NODE
#include <string>
#include "Account.h"

using namespace std;

class Node
{
	public:
		Node(Account data, Node* next=NULL, Node* prev=NULL);
		Node* get_next();
		Node* get_prev();
		Account get_data();
		void set_next(Node* newNext);
		void set_prev(Node* newPrev);
		void set_data(Account newData);
		
	private:
		Account data;
		Node* next;
		Node* prev;
};

ostream& operator << (ostream&, Node*);

#endif