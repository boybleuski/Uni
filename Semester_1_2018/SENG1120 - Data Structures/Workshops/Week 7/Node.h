#ifndef SAM_NODE
#define SAM_NODE
#include <string>
#include "Account.h"

using namespace std;

template <typename value_type>
class Node
{
	public:
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

template <typename value_type>
ostream& operator << (ostream&, Node<value_type>*);

#endif
