public interface Speak {
    void speak();
}

public abstract class Animal implements Speak {
    ...
}

public class Dog extends Animal {
    ...

    @Override
    public void speak() {
        System.out.println("Guau!");
    }
}

public class Cat extends Animal {
    ...

    @Override
    public void speak() {
        System.out.println("Miau!");
    }
}

public class Human extends Animal {
    ...

    @Override
    public void speak() {
        System.out.println("Hello!");
    }
}