/*
  Point3D.cpp implements a Point3D class that represents a Cartesian point on
  three axes.
  Author: Sam Dolbel
  Last modified: 13/6/2018
*/

#include <iostream>
#include <cstdlib>
#include "Point3D.h"

using namespace std;

Point3D::Point3D(int newX, int newY, int newZ)
{
  x = newX;
  y = newY;
  z = newZ;
}

int Point3D::get_z() const
{
  return z;
}

void Point3D::set_z(int newZ)
{
  z = newZ;
}

bool Point3D::operator== (const Point3D& point2)
{
  if (get_x() == point2.get_x() && get_y() == point2.get_y() && get_z() == point2.get_z())
    return true;
  else
    return false;
}

bool Point3D::operator!= (const Point3D& point2)
{
  if (get_x() != point2.get_x() || get_y() != point2.get_y() || get_z() != point2.get_z())
    return true;
  else
    return false;
}

void Point3D::operator+= (const Point3D& point2)
{
  set_x(get_x() + point2.get_x());
  set_y(get_y() + point2.get_y());
  set_z(get_z() + point2.get_z());
}

ostream& operator<< (ostream& cout, Point3D& point1)
{
  cout << "(" << point1.get_x() << "," << point1.get_y() << "," << point1.get_z() << ")";
  return cout;
}
