package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void convertJava(BufferedImage image, ImageFormat format, OutputStream os) throws IOException {
        ImageIO.write(image, format.name().toLowerCase(), os);
    }

    ...
}
