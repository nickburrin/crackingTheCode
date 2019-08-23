package app;

public class Equation
{
    double slope;
    double yIntercept;

    public Equation(Point p1, Point p2) {
        slope = calcSlope(p1, p2);
        yIntercept = calcYIntercept(slope, p1);
    }

    private static double calcYIntercept(double m, Point p) {
        return p.getY() - m * p.getX();
    }

    private static double calcSlope(Point p1, Point p2) {
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
	}

    public double calcXIntersection(Equation other) {
        return (other.yIntercept - this.yIntercept) / (this.slope - other.slope);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("y = ").append(slope).append("x + ").append(yIntercept).toString();
    }
}