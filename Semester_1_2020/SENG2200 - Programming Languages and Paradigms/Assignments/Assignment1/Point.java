package assignment1;

public class Point
{
  private float x;
  private float y;

  public Point(float _x, float _y)
  {
    x = _x;
    y = _y;
  }

  public float GetX()
  {
    return x;
  }

  public float GetY()
  {
    return y;
  }

  public String ToString()
  {
    return "(" + Double.ToString(x) + " , " + Double.ToString(y) + ")";
    // return "(" + String.format("%d", x) + " , " + String.format("%d", y) + ")";
  }
}
