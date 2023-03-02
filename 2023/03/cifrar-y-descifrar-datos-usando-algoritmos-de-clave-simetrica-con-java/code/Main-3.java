package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    ...
}

