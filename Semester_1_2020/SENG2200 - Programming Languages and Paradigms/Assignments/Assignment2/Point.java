/**
 * Defines a Point class, a set of (x,y) Cartesian coordinates
 *
 * Author: Sam Dolbel
 * Date created: 18/3/2020
 * Date modified: 22/3/2020
 */
import java.util.Formatter;

public class Point
{
  private double x;	// the x-coordinate
  private double y;	// the y-coordinate

  public Point(double _x, double _y)
  {
    x = _x;
    y = _y;
  }

  public double GetX()
  {
    return x;	// return the x-coordinate
  }

  public double GetY()
  {
    return y;	// return the y-coordinate
  }

  public String ToString()	// return the point as a string - each coordinate is rounded to two decimal places
  {
    return "(" + String.format("%4.2f", x) + " , " + String.format("%4.2f", y) + ")";
  }
}
