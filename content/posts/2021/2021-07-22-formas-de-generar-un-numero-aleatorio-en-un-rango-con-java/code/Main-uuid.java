package io.github.picodotdev.blogbitix.javarandom;

...

public class Main {

    public static void main(String[] args) {
        ...

        System.out.println("\nUUID");
        String uuidNumbers = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> RandomUtil.getUUID().toString())
                .collect(Collectors.joining(", "));
        System.out.printf("Numbers: %s%n", uuidNumbers);
    }
}
