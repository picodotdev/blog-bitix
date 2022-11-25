package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void scaleThumbnailator(InputStream is, OutputStream os) throws IOException {
        Thumbnails.of(is)
                .size(650, 450)
                .outputQuality(0.9)
                .outputFormat("jpg")
                .toOutputStream(os);
    }

    ...
}
