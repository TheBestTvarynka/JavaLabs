package entity;

public class Point {
    double x;
    double y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long longX = Double.doubleToLongBits(x);
        long longY = Double.doubleToLongBits(y);
        result = 31 * result + (int)(longX ^ (longX >>> 32));
        result = 31 * result + (int)(longY ^ (longY >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
