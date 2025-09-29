package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static byte[] encrypt(SecretKey key, InputStream stream) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        CipherInputStream cipherInputStream = new CipherInputStream(stream, cipher);
        return cipherInputStream.readAllBytes();
    }
    
    ...
}

