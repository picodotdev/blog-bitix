Optional<Car> car = Optional.ofNullable(otherCar);
car.ifPresent(Car::startEngine);