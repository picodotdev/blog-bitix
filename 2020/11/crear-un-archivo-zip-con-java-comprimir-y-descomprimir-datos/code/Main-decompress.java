package io.github.picodotdev.blogbitix.javazip;

...

public class Main {

    private static final List<String> FILES = List.of("alice29.txt", "asyoulik.txt", "cp.html", "fields.c", "grammar.lsp", "kennedy.xls", "lcet10.txt", "plrabn12.txt", "ptt5", "sum", "xargs.1");

    ...

    public void decompress() throws Exception {
        ZipFile zf = new ZipFile(new File("target/file.zip"));
        try (zf) {
            Enumeration<? extends ZipEntry> entries = zf.entries();
            for (ZipEntry ze : Collections.list(entries)) {
                System.out.printf("Inflating %s (compressed: %s, size: %s, ratio: %.2f)%n", ze.getName(), ze.getCompressedSize(), ze.getSize(), (double) ze.getSize() / ze.getCompressedSize());
                InputStream is = zf.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(new File("target", ze.getName()));
                try (is; fos) {
                    fos.write(is.readAllBytes());
                }
            }
        }
    }

    ...
}
