package io.github.picodotdev.blogbitix.javahashing;

...

public class Main {

    public static void main(String[] args) throws Exception {
        Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
        System.out.println("Supported algorithms: " + messageDigest.stream().sorted().collect(Collectors.joining(",")));

        String password = "rw@wbnaq2R@DS#u3o7hxWckqhfkzbT";

        ...
}
