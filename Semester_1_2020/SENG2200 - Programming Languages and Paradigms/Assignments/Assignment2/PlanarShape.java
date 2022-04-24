abstract class PlanarShape
{
    protected final double PI = 3.141;
    PlanarShape previous;	// the previous polygon in the list of polygons
    PlanarShape next;		// the next polygon in the list of polygons
    public abstract String ToString();
    public abstract double Area();
    public abstract double OriginDistance();

    public PlanarShape GetPrevious()	// return the previous polygon in the list of polygons
    {
        if (previous == null)
            return this;
        else
            return previous;
    }

    public PlanarShape GetNext()	// return the next polygon in the list of polygons
    {
        if (next == null)
            return this;
        else
            return next;
    }

    public void SetPrevious(PlanarShape _previous) // set the previous polygon as the parameter in the list of polygons
    {
        previous = _previous;
    }

    public void SetNext(PlanarShape _next)	// set the next polygon as the parameter in the list of polygons
    {
        next = _next;
    }

    public boolean ComesBefore(PlanarShape _shape)
    {
        if (Double.compare(this.Area(), _shape.Area()) < 0)	// check if this polygon is smaller than the parameter polygon
        {
            if (Double.compare((_shape.Area() - this.Area()), (this.Area()/1000)) < 0)	// check if the difference between the areas is < 0.1%
            {
                if (Double.compare(this.OriginDistance(), _shape.OriginDistance()) < 0)	// if difference is < 0.1%, check which polygon has a point closest to the origin
                    return true;	// this is smaller
                else
                    return false;	// parameter is smaller
            }
            else
                return true;	// this is smaller
        }
        else
        {
            if (Double.compare((this.Area() - _shape.Area()), (_shape.Area()/1000)) < 0)	// check if the difference between the areas is less than 0.1%
            {
                if (Double.compare(this.OriginDistance(), _shape.OriginDistance()) < 0)	// if difference is < 0.1%, check which polygon has a point closest to the origin
                    return true;	// this is smaller
                else
                    return false;	// parameter is smaller
            }
            else
                return false;	// parameter is smaller
        }
    }
}