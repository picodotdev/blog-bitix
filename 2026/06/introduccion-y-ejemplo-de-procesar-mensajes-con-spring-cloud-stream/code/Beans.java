package io.github.picodotdev.blogbitix.springcloudstream;

...

@Component
public class Beans {

    @Bean
    Function<Message<String>, Message<String>> uppercase() {
        return new UppercaseFunction();
    }

    @Bean
    Consumer<Message<String>> logger() {
        return new LoggerConsumer();
    }
}
