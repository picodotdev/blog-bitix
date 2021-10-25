...

public class Singleton {

   private static final Singleton instance;

   private Singleton() {}

   public static Singleton getInstance() {
       if (instance == null) {
           instance = new Singleton();
       }
       return instance;
   }
}