package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    ...

    private static void asymmetricEncrypt() throws Exception {
        ...

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(8192);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("Private key: \n" + encodePem(privateKey));
        System.out.println("Public key: \n" + encodePem(publicKey));

        ...
    }

    ...
}

