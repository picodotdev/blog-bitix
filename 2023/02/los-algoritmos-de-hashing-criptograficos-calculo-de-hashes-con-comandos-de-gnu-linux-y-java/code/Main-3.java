package io.github.picodotdev.blogbitix.javahashing;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ...

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ftp.rediris.es/mirror/archlinux/iso/latest/archlinux-x86_64.iso"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<InputStream> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofInputStream());

        ...

        String shasumCalculated = calculateHash("SHA-256", response.body());
        System.out.printf("%s: %s%n", "SHA-256", shasumCalculated);
        ...
    }

    ...

    private static String calculateHash(String algorithm, InputStream stream) throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        try (DigestInputStream digestInputStream = new DigestInputStream(stream, messageDigest)) {
            digestInputStream.transferTo(OutputStream.nullOutputStream());
            byte[] hash = messageDigest.digest();
            return HexFormat.of().formatHex(hash);
        }
    }
}
