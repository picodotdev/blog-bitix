public class Main {

    public static void main(String[] args) {
        String shapeType = args[0];
        Shape shape1 = ShapeFactory.create(shapeType);
        System.out.printf("Shape perimeter: %s%n", shape1.getPerimeter());
        System.out.printf("Shape area: %s%n", shape1.getArea());

        Shape shape2 = ShapeFactory.createSquare(2d);
        System.out.printf("Shape perimeter: %s%n", shape2.getPerimeter());
        System.out.printf("Shape area: %s%n", shape2.getArea());

        Shape shape3 = ShapeFactory.createCircle(2d);
        System.out.printf("Shape perimeter: %s%n", shape3.getPerimeter());
        System.out.printf("Shape area: %s%n", shape3.getArea());
    }
}