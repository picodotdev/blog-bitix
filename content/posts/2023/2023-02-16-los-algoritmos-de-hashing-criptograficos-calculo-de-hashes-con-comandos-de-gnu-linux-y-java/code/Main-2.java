package io.github.picodotdev.blogbitix.javahashing;

...

public class Main {

    public static void main(String[] args) throws Exception {
        Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
        System.out.println("Supported algorithms: " + messageDigest.stream().sorted().collect(Collectors.joining(",")));

        String password = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";

        System.out.printf("%s: %s%n", "MD5", calculateHash("MD5", password));
        System.out.printf("%s: %s%n", "SHA-1", calculateHash("SHA-1", password));
        System.out.printf("%s: %s%n", "SHA-256", calculateHash("SHA-256", password));
        System.out.printf("%s: %s%n", "SHA-512", calculateHash("SHA-512", password));
        System.out.printf("%s: %s%n", "SHA3-256", calculateHash("SHA3-256", password));
        System.out.printf("%s: %s%n", "SHA3-512", calculateHash("SHA3-512", password));

        ...
    }

    private static String calculateHash(String algorithm, String content) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest();
        return HexFormat.of().formatHex(hash);
    }

    ...
}
