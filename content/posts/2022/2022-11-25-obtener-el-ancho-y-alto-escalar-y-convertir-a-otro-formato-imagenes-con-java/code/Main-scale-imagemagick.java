package io.github.picodotdev.blogbitix.javaimageprocess;

...

public class Main {

    ...

    private static void scaleImageMagick(InputStream is, OutputStream os) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder().command("convert", "-resize", "650x450", "jpeg:-", "jpeg:-");
        Process process = builder.start();

        process.getOutputStream().write(is.readAllBytes());
        process.getOutputStream().close();
        os.write(process.getInputStream().readAllBytes());

        process.waitFor();
        int value = process.exitValue();
        if (value != 0) {
            throw new IOException(MessageFormat.format("Código de salida con error (%d)", value));
        }
    }

    ...
}
