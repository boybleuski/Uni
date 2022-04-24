#ifndef SAM_POINT
#define SAM_POINT
#include <cmath>
#include <iostream>

using namespace std;

class Point
{
	public:
		Point();
		Point(double,double);
		void set_x(double);
		void set_y(double);
		double get_x() const;
		double get_y() const;

	private:
		double x;
		double y;
};
ostream& operator << (ostream&, const Point&);
#endif
