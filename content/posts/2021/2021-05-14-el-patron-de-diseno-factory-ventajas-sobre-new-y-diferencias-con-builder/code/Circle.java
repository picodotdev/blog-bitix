public class Circle extends Shape {

    private double radious;

    public Circle(double radious) {
        this.radious = radious;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radious;
    }

    @Override
    public double getArea() {
        return Math.PI * radious * radious;
    }
}