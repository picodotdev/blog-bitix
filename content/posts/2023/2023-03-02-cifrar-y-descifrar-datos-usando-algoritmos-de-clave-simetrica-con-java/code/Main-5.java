package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static byte[] encrypt(SecretKey key, String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }

    ...
}

