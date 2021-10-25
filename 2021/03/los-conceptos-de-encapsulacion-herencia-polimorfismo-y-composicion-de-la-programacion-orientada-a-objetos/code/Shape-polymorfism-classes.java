public abstract class Shape {
    ...

    public abstract double calculatePerimeter();
    public abstract double calculateArea();
}

public class Circle extends Shape {

    private double radious;

    ...

    public double calculatePerimeter() {
        return 2 * Math.PI * radious;
    }

    public double calculateArea() {
        return Math.PI * radious * radious;
    }
}

public class Rectangle extends Shape {

    private double tall;
    private double with;

    ...

    public double calculatePerimeter() {
        return 2 * (tall + with);
    }

    public double calculateArea() {
        return tall * with;
    }
}