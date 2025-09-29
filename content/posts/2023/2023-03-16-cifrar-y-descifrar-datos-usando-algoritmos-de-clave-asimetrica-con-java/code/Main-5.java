package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void asymmetricEncrypt() throws Exception {
        ...

        String text = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";

        byte[] encrypted = encrypt(publicKey, text);
        String decrypted = new String(decrypt(privateKey, encrypted));

        System.out.println("Plain text: " + text);
        System.out.println("Public key encrypted: " + HexFormat.of().formatHex(encrypted));
        System.out.println("Private key decrypted: " + decrypted);
    }

    ...

    private static byte[] decrypt(PrivateKey key, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encrypted);
    }

    ...
}