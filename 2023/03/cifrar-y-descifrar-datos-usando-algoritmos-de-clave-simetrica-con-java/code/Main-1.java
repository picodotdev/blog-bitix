package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ...

        symetricEncrypt();
    }

    ...

    private static void symetricEncrypt() throws Exception {
        Set<String> keyGenerators = Security.getAlgorithms("KeyGenerator");
        System.out.println("Supported key generators: " + keyGenerators.stream().sorted().collect(Collectors.joining(",")));

        Set<String> secretKeyFactories = Security.getAlgorithms("SecretKeyFactory");
        System.out.println("Supported key factory: " + secretKeyFactories.stream().sorted().collect(Collectors.joining(",")));

        Set<String> chipers = Security.getAlgorithms("Cipher");
        System.out.println("Supported ciphers: " + chipers.stream().sorted().collect(Collectors.joining(",")));

        Set<String> macs = Security.getAlgorithms("Mac");
        System.out.println("Supported macs: " + macs.stream().sorted().collect(Collectors.joining(",")));

        ...
    }

    ...
}

