#include <cmath>
#include <iostream>
#include "Point.h"

using namespace std;

Point::Point()
{
	x = 0.0;
	y = 0.0;
}

Point::Point(double newX, double newY)
{
	x = newX;
	y = newY;
}

void Point::set_x(double newX)
{
	x = newX;
}

void Point::set_y(double newY)
{
	y = newY;
}

double Point::get_x() const
{
	return x;
}

double Point::get_y() const
{
	return y;
}

ostream& operator << (ostream& osObject, const Point& myPoint)
{
	osObject << "(" << myPoint.get_x() << "," << myPoint.get_y() << ")";
	return osObject;
}
