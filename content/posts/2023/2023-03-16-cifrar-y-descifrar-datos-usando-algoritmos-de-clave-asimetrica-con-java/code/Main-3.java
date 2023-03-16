package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void asymmetricEncrypt() throws Exception {
        ...

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("Private key: \n" + encodePem(privateKey));
        System.out.println("Public key: \n" + encodePem(publicKey));

        byte[] encrypted = encrypt(publicKey, text);
        String decrypted = new String(decrypt(privateKey, encrypted));

        System.out.println("Plain text: " + text);
        System.out.println("Public key encrypted: " + HexFormat.of().formatHex(encrypted));
        System.out.println("Private key decrypted: " + decrypted);
    }

    ...

    public static String encodePem(PrivateKey privateKey) throws Exception {
        PemObject privateKeyPemObject = new PemObject("RSA PRIVATE KEY", privateKey.getEncoded());
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
        pemWriter.writeObject(privateKeyPemObject);
        pemWriter.close();
        return stringWriter.toString();
    }

    public static String encodePem(PublicKey publicKey) throws Exception {
        PemObject privateKeyPemObject = new PemObject("RSA PUBLIC KEY", publicKey.getEncoded());
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
        pemWriter.writeObject(privateKeyPemObject);
        pemWriter.close();
        return stringWriter.toString();
    }
}

