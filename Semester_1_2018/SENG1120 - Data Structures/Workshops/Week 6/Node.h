#ifndef SAM_NODE
#define SAM_NODE
#include <string>
#include "Account.h"

using namespace std;

class Node
{
	public:
		typedef string value_type;
		Node(value_type data, Node* next=NULL, Node* prev=NULL);
		Node* get_next();
		Node* get_prev();
		value_type get_data();
		void set_next(Node* newNext);
		void set_prev(Node* newPrev);
		void set_data(value_type newData);

	private:
		value_type data;
		Node* next;
		Node* prev;
};

//ostream& operator << (ostream&, Node*);

#endif
