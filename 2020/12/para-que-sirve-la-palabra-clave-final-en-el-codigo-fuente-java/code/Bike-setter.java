...

public class Bike { 

   ...

   public void setSpeed(final int speed) {
       speed = speed; // Compiler error: Cannot assign a value to final variable 'speed'
   }

   public void setMaxSpeed(final int speed) {
        MAX_SPEED = speed; // Compiler error: Cannot assign a value to final variable 'MAX_SPEED'
   }

   ...
} 