package app;

public class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    } 

    public double getY() {
        return y;
    }

	public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    } 
    
    public boolean isBetween(Point start, Point end) {
        return isBetween(start.x, this.x, end.x) && isBetween(start.y, this.y, end.y);
    }

    private boolean isBetween(double start, double mid, double end) {
        if (start > end)
            return end <= mid && mid <= start;
        else
            return start <= mid && mid <= end;
    }

    public static void swap(Point p1, Point p2) {
        double tempX = p1.getX();
        double tempY = p1.getY();

        p1.setLocation(p2.getX(), p2.getY());
        p2.setLocation(tempX, tempY);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(x).append(", ").append(y).append("]").toString();
    }
}