#ifndef SAM_LINKEDLIST
#define SAM_LINKEDLIST
#include "Node.h"

class LinkedList
{
	public:
		typedef Account value_type;
		LinkedList();
		~LinkedList();
		void add_to_head(value_type);
		void add_to_tail(value_type);
		void add_current(value_type);
		void remove(string);
		void remove_from_head();
		void remove_from_tail();
		void remove_from_current();
		void start();
		void end();
		void forward();
		void back();
		void print_list();
		value_type get_current();
		int size();

	private:
		Node* head;
		Node* tail;
		Node* current;
		int listSize;
};

#endif
