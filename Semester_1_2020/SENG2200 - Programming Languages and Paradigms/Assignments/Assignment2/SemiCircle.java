/**
 * Defines a SemiCircle class - two Cartesian points (origin point and a
 * point that intersects the perimeter in a perpendicular line)
 *
 * Author: Sam Dolbel
 * Date created: 11/5/2020
 * Date modified: 15/5/2020
 */

import java.lang.Math;

public class SemiCircle extends PlanarShape
{
    private Point originPoint;
    private Point outerPoint;

    public SemiCircle(Point _originPoint, Point _outerPoint)
    {
        originPoint = _originPoint;
        outerPoint = _outerPoint;
    }

    public double Area()	// get the size in area of the polygon
    {
        double radius = Math.sqrt(Math.pow(originPoint.GetX()-outerPoint.GetX(), 2) + Math.pow(originPoint.GetY()-outerPoint.GetY(), 2));
        return (PI * Math.pow(radius, 2)) / 2;
    }

    public double OriginDistance()	// get the distance of the closest point from (0,0)
    {
        double x = originPoint.GetX() - Math.abs(originPoint.GetY() - outerPoint.GetY());
        double y = originPoint.GetY() + Math.abs(originPoint.GetX() - outerPoint.GetX());
        Point endPointA = new Point(x, y);
        x = originPoint.GetX() + Math.abs(originPoint.GetY() - outerPoint.GetY());
        y = originPoint.GetY() - Math.abs(originPoint.GetX() - outerPoint.GetX());
        Point endPointB = new Point(x, y);
        Point[] pointList = new Point[] {outerPoint, endPointA, endPointB};

        double distance = Math.sqrt(originPoint.GetX()*originPoint.GetX() + originPoint.GetY()*originPoint.GetY());
        for (int i=0; i<pointList.length; i++)
        {
            double tempDistance = Math.sqrt(pointList[i].GetX()*pointList[i].GetX() + pointList[i].GetY()*pointList[i].GetY());
            if (tempDistance < distance)
                distance = tempDistance;
        }

        return distance;
    }

    public String ToString()	// return the polygon as a string - points are printed with Point.ToString() and area is rounded to two decimal places
    {
        String polygonString = "SEMI=[";	// opening brace that starts an array
        polygonString += originPoint.ToString();

        polygonString += ("]: " + String.format("%6.2f", Area()));	// closing brace that ends an array + polygon area
        return polygonString;
    }
}
