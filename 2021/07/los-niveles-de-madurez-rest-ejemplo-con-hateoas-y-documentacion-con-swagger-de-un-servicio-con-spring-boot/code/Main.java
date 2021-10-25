package io.github.picodotdev.blogbitix.springresthateoas;

...

@SpringBootApplication
public class Main {

    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
