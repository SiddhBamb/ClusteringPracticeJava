// point class for euclidean points
public class Point
{
    public double x = 0;
    public double y = 0;
    
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double dist(Point p)
    {
        return Math.sqrt(Math.pow(x-p.x, 2) + Math.pow(y-p.y, 2));
    } 

    public boolean equals(Object o)
    {
        if(o==this)
            return true;
        if(!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return p.x == this.x && p.y == this.y;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}