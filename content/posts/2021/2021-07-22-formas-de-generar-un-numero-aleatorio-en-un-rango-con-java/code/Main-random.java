package io.github.picodotdev.blogbitix.javarandom;

...

public class Main {

    public static void main(String[] args) {
        System.out.println("Random");
        String randomNumbers = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Integer.toString(RandomUtil.getInt(0, 10)))
                .collect(Collectors.joining(", "));
        System.out.printf("Numbers: %s%n", randomNumbers);

        ...
    }
}
