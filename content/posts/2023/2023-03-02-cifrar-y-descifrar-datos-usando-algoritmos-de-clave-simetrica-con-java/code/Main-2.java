package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void symetricEncrypt() throws Exception {
        String text = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";
        String password = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";
        String salt = "%@&LN4CLT95yMEHNSettCAaQAcHZbh";

        SecretKey key = generateKey();
        SecretKey passwordKey = generateKey(password, salt);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] keyEncrypted = encrypt(key, text);
        byte[] passwordEncrypted = encrypt(passwordKey, text);
        byte[] inputStreamEncrypted = encrypt(key, Main.class.getResourceAsStream("/text.txt"));

        System.out.println("Plain text: " + password);
        System.out.println("Key encrypted: " + HexFormat.of().formatHex(keyEncrypted));
        System.out.println("Password key encrypted: " + HexFormat.of().formatHex(passwordEncrypted));
        System.out.println("InputStream key encrypted: " + HexFormat.of().formatHex(inputStreamEncrypted));
        System.out.println("Key decrypted: " + new String(decrypt(key, keyEncrypted)));
        System.out.println("Password key decrypted: " + new String(decrypt(passwordKey, passwordEncrypted)));
        System.out.println("HMAC: " + calculateHmac(key, text));
    }

    ...
}

