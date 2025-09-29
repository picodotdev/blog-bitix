public class ShapeFactory {

   public static Shape create(String type) {
       if (type.equals("square")) {
           return new Square(1);
       } else if (type.equals("circle")) {
           return new Circle(1);
       } else {
           throw new IllegalArgumentException();
       }
   }

   public static Square createSquare(double length) {
       return new Square(length);
   }

   public static Circle createCircle(double radious) {
       return new Circle(radious);
   }
}