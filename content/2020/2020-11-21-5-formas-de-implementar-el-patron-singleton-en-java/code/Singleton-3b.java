...

public class Singleton {

   private static final Singleton instance;

   static {
       instance = new Singleton();
   }

   private Singleton() {}

   public static Singleton getInstance() {
       return instance;
   }
}