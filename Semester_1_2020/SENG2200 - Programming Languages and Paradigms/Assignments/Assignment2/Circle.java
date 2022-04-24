/**
 * Defines a Circle class - one Cartesian origin point and
 * a radius.
 *
 * Author: Sam Dolbel
 * Date created: 11/5/2020
 * Date modified: 15/5/2020
 */

import java.lang.Math;

public class Circle extends PlanarShape
{
    private Point originPoint;
    private double radius;

    public Circle(Point _originPoint, double _radius)
    {
        originPoint = _originPoint;
        radius = _radius;
    }

    public double Area()	// get the size in area of the polygon
    {
        return PI * Math.pow(radius, 2);
    }

    public double OriginDistance()	// get the distance of the closest point from the origin
    {
        return Math.sqrt(Math.pow(originPoint.GetX(), 2) + Math.pow(originPoint.GetY(), 2)) - radius;
    }

    public String ToString()	// return the polygon as a string - points are printed with Point.ToString() and area is rounded to two decimal places
    {
        String polygonString = "CIRC=[";	// opening brace that starts an array
        polygonString += originPoint.ToString();

        polygonString += ("]: " + String.format("%6.2f", Area()));	// closing brace that ends an array + polygon area
        return polygonString;
    }
}