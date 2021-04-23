package io.github.picodotdev.blogbitix.javaretrofit;

...

@SpringBootApplication
public class Main implements CommandLineRunner {

    ...

    @Override
    public void run(String... args) throws Exception {
        Service service = buildService();

        String r1 = service.message("es-ES", "", UUID.randomUUID().toString()).execute().body();
        String r2 = service.message("es-ES", "Java", UUID.randomUUID().toString()).execute().body();
        String r3 = service.message("en-GB", "", UUID.randomUUID().toString()).execute().body();
        String r4 = service.message("en-GB", "Java", UUID.randomUUID().toString()).execute().body();

        System.out.printf("Result: %s%n", r1);
        System.out.printf("Result: %s%n", r2);
        System.out.printf("Result: %s%n", r3);
        System.out.printf("Result: %s%n", r4);
    }

    ...
}
