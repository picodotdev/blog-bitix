Car car = new Car(Branc.RENAULT, Color.LIGHT_GREY, 160);
Vehicle vehicle = car;

...

if (vehicle instanceof Car) {
    Car car = (Car) vehicle;  
}