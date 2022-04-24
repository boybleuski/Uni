//LinkedListDemo - Main Program
/******************************/
//Author: Alex Mendes
//Course: SENG1120
//Program Description: This program demonstrates the basic functionality of a linked list that stores text.
//It will demo the functions of a linked list which stores general text, one token in each node.
//The program adds content to a linked list, removes individual nodes, counts the number of words, and concatenates sentences.
//Finally, a more advanced feature is text reversal, which reserves the order of the words in a sentence.

#include <iostream>
#include <cstdlib>
#include <string>
#include "LinkedList.h"

using namespace std;

int main()
{
	LinkedList firstSentence;
  firstSentence.fill_list("This is the first assignment for the course SENG1120");
	LinkedList secondSentence;
	secondSentence.fill_list("It tests the functionality of a linked list");

	cout << "Start sentences:" << endl;
	cout << "Sentence 1: " << firstSentence  <<  endl;
	cout << "Sentence 2: " << secondSentence <<  endl << endl;

	cout << "Concatenating the two sentences onto sentence '1':" << endl;
	firstSentence += secondSentence;
    cout << "Sentence 1: " << firstSentence  << endl;
	cout << "Sentence 2: " << secondSentence << endl << endl;

	cout << "Removing the word 'the' from sentence '1':" << endl;
    firstSentence.remove("the");
    cout << "Sentence 1: " << firstSentence  << endl;
	cout << "Sentence 2: " << secondSentence << endl << endl;

	cout << "Removing the word 'This' from both sentences:" << endl;
    firstSentence.remove("This");
    secondSentence.remove("This");
    cout << "Sentence 1: " << firstSentence  << endl;
	cout << "Sentence 2: " << secondSentence << endl << endl;

	cout << "Removing the word 'list' from sentence '2':" << endl;
    secondSentence.remove("list");
    cout << "Sentence 1: " << firstSentence  << endl;
	cout << "Sentence 2: " << secondSentence << endl << endl;

	// If you are a SENG6120 student, please uncomment the next four lines.
	// cout << "Reversing sentence '2':" << endl;
    // secondSentence.reverse();
    // cout << "Sentence 1: " << firstSentence  << endl;
	// cout << "Sentence 2: " << secondSentence << endl << endl;

	cout << "The program has finished." << endl;
	return 0;
}
