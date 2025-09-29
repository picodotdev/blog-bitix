package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static BufferedImage readImage(InputStream is) throws IOException {
        return ImageIO.read(is);
    }

    private static void writeImage(BufferedImage image, ImageFormat format, OutputStream os) throws IOException {
        ImageIO.write(image, format.name(), os);
    }

    ...
}
