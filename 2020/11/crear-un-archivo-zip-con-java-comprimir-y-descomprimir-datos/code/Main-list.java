package io.github.picodotdev.blogbitix.javazip;

...

public class Main {

    ...

    public void list() throws Exception {
        ZipFile zf = new ZipFile(new File("target/file.zip"));
        try (zf) {
            Enumeration<? extends ZipEntry> entries = zf.entries();
            for (ZipEntry ze : Collections.list(entries)) {
                System.out.printf("File %s (compressed: %s, size: %s, ratio: %.2f)%n", ze.getName(), ze.getCompressedSize(), ze.getSize(), (double) ze.getSize() / ze.getCompressedSize());
            }
        }
    }

    ...
}
