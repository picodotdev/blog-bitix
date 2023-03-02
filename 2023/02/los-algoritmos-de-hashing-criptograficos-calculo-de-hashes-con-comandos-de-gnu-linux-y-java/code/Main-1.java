package io.github.picodotdev.blogbitix.javahashingencrypt;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ...
        hashing();

        ...
    }

    private static void hashing() throws Exception {
        Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
        System.out.println("Supported algorithms: " + messageDigest.stream().sorted().collect(Collectors.joining(",")));

        ...
    }

    ...
}

