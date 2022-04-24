/*
  Point3D.h defines a Point3D class that represents a Cartesian point on
  three axes.
  Author: Sam Dolbel
  Last modified: 13/6/2018
*/

#ifndef SAM_POINT3D
#define SAM_POINT3D

#include <cstdlib>
#include <iostream>
#include "Point.h"

using namespace std;

class Point3D: public Point
{
  public:
    //constructor
    Point3D(int x=0, int y=0, int z=0);

    //queriers
    int get_z() const;

    //mutators
    void set_z(int);
    bool operator== (const Point3D&);
    bool operator!= (const Point3D&);
    void operator+= (const Point3D&);

  protected:
    int z;
};

ostream& operator<< (ostream&, Point3D&)
#endif
