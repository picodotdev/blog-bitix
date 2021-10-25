package io.github.picodotdev.blogbitix.javapact;

...

@SpringBootApplication
public class Main {

    @Autowired
    private ServletWebServerApplicationContext webServerContext;

    @Bean
    OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Bean
    Service buildService(OkHttpClient client) {
        return new ServiceClient(client, String.format("http://localhost:%s/", webServerContext.getWebServer().getPort()));
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
