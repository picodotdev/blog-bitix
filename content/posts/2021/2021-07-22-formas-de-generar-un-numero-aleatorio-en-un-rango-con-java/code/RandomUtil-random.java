package io.github.picodotdev.blogbitix.javarandom;

...

public class RandomUtil {

    public static int getInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    ...
}