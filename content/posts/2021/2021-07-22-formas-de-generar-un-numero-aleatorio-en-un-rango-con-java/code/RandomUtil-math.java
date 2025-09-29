package io.github.picodotdev.blogbitix.javarandom;

...

public class RandomUtil {

    ...

    public static int getIntMath(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    ...
}