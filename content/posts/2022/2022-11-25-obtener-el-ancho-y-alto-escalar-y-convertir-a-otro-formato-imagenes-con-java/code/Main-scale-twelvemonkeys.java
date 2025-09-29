package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void scaleTwelvemonkeys(BufferedImage image, OutputStream os) throws IOException, InterruptedException {
        Resolution scaledResolution = new Resolution(image.getWidth(), image.getHeight()).scale(650, 450);
        BufferedImageOp resampler = new ResampleOp(scaledResolution.getWidth(), scaledResolution.getHeight(), ResampleOp.FILTER_LANCZOS);
        BufferedImage scaledImage = resampler.filter(image, null);
        writeImage(scaledImage, ImageFormat.JPG, os);
    }

    ...
}
