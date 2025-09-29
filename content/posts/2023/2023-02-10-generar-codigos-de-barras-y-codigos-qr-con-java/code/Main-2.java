package io.github.picodotdev.blogbitix.javabarcode;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ...

        {
            QrCode qrCode = new QrCode();
            qrCode.setContent("1234567890");

            int width = qrCode.getWidth();
            int height = qrCode.getHeight();

            BufferedImage image = new BufferedImage(width * 8, height * 8, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Java2DRenderer renderer = new Java2DRenderer(g2d, 8, Color.WHITE, Color.BLACK);
            renderer.render(qrCode);

            ImageIO.write(image, "png", new File("qr-code.png"));
        }

        {
            QrCode qrCode = new QrCode();
            qrCode.setContent("https://picodotdev.github.io/blog-bitix/");

            int width = qrCode.getWidth();
            int height = qrCode.getHeight();

            BufferedImage image = new BufferedImage(width * 8, height * 8, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Java2DRenderer renderer = new Java2DRenderer(g2d, 8, Color.YELLOW, Color.BLUE);
            renderer.render(qrCode);

            ImageIO.write(image, "png", new File("qr-code-blogbitix.png"));
        }
    }
}
