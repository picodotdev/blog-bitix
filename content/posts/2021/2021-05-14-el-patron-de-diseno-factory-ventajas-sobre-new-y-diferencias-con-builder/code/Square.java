public class Square extends Shape {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double getPerimeter() {
        return 4 * length;
    }

    @Override
    public double getArea() {
        return 2 * length;
    }
}