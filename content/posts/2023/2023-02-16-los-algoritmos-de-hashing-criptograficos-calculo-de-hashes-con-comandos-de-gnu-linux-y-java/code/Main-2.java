package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void hashing() throws Exception {
        ...

        String password = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";

        System.out.println("Plain text: " + password);
        System.out.println("MD5: " + calculateHash("MD5", password));
        System.out.println("SHA-1: " + calculateHash("SHA-1", password));
        System.out.println("SHA-256: " + calculateHash("SHA-256", password));
        System.out.println("SHA-512: " + calculateHash("SHA-512", password));
        System.out.println("SHA3-256: " + calculateHash("SHA3-256", password));
        System.out.println("SHA3-512: " + calculateHash("SHA3-512", password));

        ...
    }
    
    private static String calculateHash(String algorithm, String content) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest();
        return HexFormat.of().formatHex(hash);
    }

    ...
}

