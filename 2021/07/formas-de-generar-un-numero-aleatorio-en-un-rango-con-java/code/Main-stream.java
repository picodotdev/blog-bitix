package io.github.picodotdev.blogbitix.javarandom;

...

public class Main {

    public static void main(String[] args) {
        ...

        System.out.println("\nStream");
        String streamNumbers = RandomUtil.getIntStream(1, 10, 10)
                .mapToObj(i -> Integer.toString(i))
                .collect(Collectors.joining(", "));
        System.out.printf("Numbers: %s%n", streamNumbers);

        ...
    }
}
