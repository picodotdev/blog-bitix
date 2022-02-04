package io.github.picodotdev.blogbitix.javazip;

...

public class Main {

    ...

    public void compress() throws Exception {
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("target/file.zip"))));
        try (zos) {
            for (String f : FILES) {
                System.out.printf("Deflating %s...%n", f);
                InputStream resource = getClass().getClassLoader().getResourceAsStream("cantrbry/" + f);

                zos.putNextEntry(new ZipEntry(f));
                zos.write(resource.readAllBytes());
                zos.closeEntry();
            }
        }
    }

    ...
}
