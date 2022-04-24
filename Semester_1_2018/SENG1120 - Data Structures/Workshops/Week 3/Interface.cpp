#include <cmath>
#include <iostream>
#include "Point.h"

using namespace std;

double length(Point* apoint1, Point* apoint2)
{
	double full_x = apoint1->get_x() - apoint2->get_x();
	double full_y = apoint1->get_y() - apoint2->get_y();
	double length = sqrt(pow(full_x, 2)) + sqrt(pow(full_y, 2));
	return length;
}

int main()
{
	Point *point1 = new Point();
	Point *point2 = new Point(0.3,1.5);

	double printY = point2->get_y();
	cout << point1->get_x() << endl;
	cout << printY << endl;
	point1->set_x(2.6);
	cout << point1->get_x() << endl;
	cout << *point1 << endl;
	cout << *point2 << endl;

	cout << length(*point1, *point2) << endl;
	return 0;
}
