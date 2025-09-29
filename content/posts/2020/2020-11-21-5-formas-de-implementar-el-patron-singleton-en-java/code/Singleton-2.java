...

public class Singleton {

   private static final Singleton instance;

   private Singleton() {}

   public synchronized static Singleton getInstance() {
       if (instance == null) {
           instance = new Singleton();
       }
       return instance;
   }
}