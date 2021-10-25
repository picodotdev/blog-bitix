package io.github.picodotdev.blogbitix.javarandom;

...

public class RandomUtil {

    ...

    public static IntStream getIntStream(int min, int max) {
        return new Random().ints(min, max + 1);
    }

    public static IntStream getIntStream(int min, int max, int size) {
        return new Random().ints(size, min, max + 1);
    }

    ...
}