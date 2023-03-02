package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void hashing() throws Exception {
        ...

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mirror.rackspace.com/archlinux/iso/latest/archlinux-bootstrap-x86_64.tar.gz"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<InputStream> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofInputStream());
        HttpRequest shasumRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://mirror.rackspace.com/archlinux/iso/latest/sha256sums.txt"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> shasumResponse = HttpClient.newBuilder().build().send(shasumRequest, HttpResponse.BodyHandlers.ofString());

        String shasum = shasumResponse.body().lines().skip(3).findFirst().get().split(" ")[0];
        System.out.println("Arch Linux ISO (SHA-256): " + shasum);

        String shasumCalculated = calculateHash("SHA-256", response.body());
        System.out.println("SHA-256: " + shasumCalculated);
        System.out.println("sha256sum matches: " + shasum.equals(shasumCalculated));
    }

    ...

    private static String calculateHash(String algorithm, InputStream stream) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        try (
            InputStream is = new BufferedInputStream(stream, 10 * 1024 * 1024);
            DigestInputStream digestInputStream = new DigestInputStream(is, messageDigest)
        ) {
            digestInputStream.transferTo(OutputStream.nullOutputStream());
            byte[] hash = messageDigest.digest();
            return HexFormat.of().formatHex(hash);
        }
    }

    ...
}

