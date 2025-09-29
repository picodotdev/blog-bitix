package io.github.picodotdev.blogbitix.javaimageprocess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;
import net.coobird.thumbnailator.Thumbnails;

public class Main {

    public static void main(String[] args) throws Exception {
        printSupportedFormats();

        BufferedImage image = readImage(Main.class.getResourceAsStream("/gnome.jpg"));
        BufferedImage imageWebp = readImage(Main.class.getResourceAsStream("/gnome.webp"));

        BufferedImage scaledImage = scaleJava(image);
        convertJava(scaledImage, ImageFormat.JPG, new FileOutputStream("gnome-scaled-java.jpg"));

        scaleThumbnailator(Main.class.getResourceAsStream("/gnome.jpg"), new FileOutputStream("gnome-scaled-thumbnailator.jpg"));
        scaleImageMagick(Main.class.getResourceAsStream("/gnome.jpg"), new FileOutputStream("gnome-scaled-imagemagick.jpg"));
        scaleTwelvemonkeys(readImage(Main.class.getResourceAsStream("/gnome.jpg")), new FileOutputStream("gnome-scaled-twelvemonkeys.jpg"));

        printImageWidthHeight("original", image);
        printImageWidthHeight("original webp", imageWebp);
        printImageWidthHeight("scaled", scaledImage);
        printImageWidthHeight("scaled thumbnailator", readImage(new FileInputStream("gnome-scaled-thumbnailator.jpg")));
        printImageWidthHeight("scaled imagemagick", readImage(new FileInputStream("gnome-scaled-imagemagick.jpg")));
        printImageWidthHeight("scaled twelvemonkeys", readImage(new FileInputStream("gnome-scaled-twelvemonkeys.jpg")));
    }

    ...
}
