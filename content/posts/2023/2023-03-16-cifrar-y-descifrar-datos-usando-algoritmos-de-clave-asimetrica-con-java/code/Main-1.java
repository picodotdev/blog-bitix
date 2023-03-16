package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ...

        System.out.println("");
        System.out.println("Asymmetric encryption");
        asymmetricEncrypt();
    }

    private static void asymmetricEncrypt() throws Exception {
        Set<String> keypairGenerators = Security.getAlgorithms("KeyPairGenerator");
        System.out.println("Supported key generators: " + keypairGenerators.stream().sorted().collect(Collectors.joining(",")));

        ...
    }

    ...
}

