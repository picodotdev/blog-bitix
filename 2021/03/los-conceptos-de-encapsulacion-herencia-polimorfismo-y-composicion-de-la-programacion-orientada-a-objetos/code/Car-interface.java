public interface Cleanable {
    void clean();
}

public class Car extends Vehicle implements Cleanable {

    @Override
    public clean() {
        ...
    }
}