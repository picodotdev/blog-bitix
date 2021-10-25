public class Main {

    public static void main(String[] args) {
        Shape shape1 = new Square(2d);
        System.out.printf("Shape perimeter: %s%n", shape1.getPerimeter());
        System.out.printf("Shape area: %s%n", shape1.getArea());

        Shape shape2 = new Circle(2d);
        System.out.printf("Shape perimeter: %s%n", shape2.getPerimeter());
        System.out.printf("Shape area: %s%n", shape2.getArea());
    }
}