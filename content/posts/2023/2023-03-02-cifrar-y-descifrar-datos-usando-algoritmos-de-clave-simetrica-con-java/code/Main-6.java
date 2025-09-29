package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static byte[] decrypt(SecretKey key, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encrypted);
    }

    ...
}

