package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void printImageWidthHeight(String name, BufferedImage image) {
        System.out.printf("Width (%s): %s%n", name, image.getWidth());
        System.out.printf("Height (%s): %s%n", name,  image.getHeight());
    }

    ...
}
