package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void printSupportedFormats() {
        String[] readerFormatNames = javax.imageio.ImageIO.getReaderFormatNames();
        String[] writerFormatNames = javax.imageio.ImageIO.getWriterFormatNames();

        System.out.printf("Reader format names: %s%n", String.join(",", readerFormatNames));
        System.out.printf("Writer format names: %s%n", String.join(",", writerFormatNames));
    }

    ...
}
