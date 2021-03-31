public enum Color {
    BLACK, WHITE, LIGHT_GREY, RED, BLUE
}

public enum Brand {
    RENAULT, PEUGEOT, FORD, TOYOTA, MERCEDES, TESLA
}

public enum Status {
    STOPED, ON
}

public enum Gasoline {
    OCTANE_95, OCTANE_98, GASOLEO_A
}

public class Car {

    private Brand brand
    private Color color;
    private int maxSpeed;
    private int speed;

    private Status status;

    public Car(Brand brand, Color color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;

        this.speed = 0;
        this.status = STOPED;
    }

    public void on() {
        this.status = ON;
    }

    public void stop() {
        this.speed = 0;
        this.status = STOPED;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void fillCombustible(Gasoline gasoline, int liters) {
        ...
    }

    ...
}