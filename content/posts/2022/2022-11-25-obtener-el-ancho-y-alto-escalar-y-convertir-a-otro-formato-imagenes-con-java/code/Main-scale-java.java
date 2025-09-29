package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static BufferedImage scaleJava(BufferedImage image) {
        Resolution scaledResolution = new Resolution(image.getWidth(), image.getHeight()).scale(650, 450);
        BufferedImage scaledImage = new BufferedImage(scaledResolution.getWidth(), scaledResolution.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.drawImage(image, 0, 0, scaledResolution.getWidth(), scaledResolution.getHeight(), null);
        graphics2D.dispose();
        return scaledImage;
    }

    ...
}
