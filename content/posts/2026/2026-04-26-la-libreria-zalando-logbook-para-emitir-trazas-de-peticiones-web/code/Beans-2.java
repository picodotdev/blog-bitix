package io.github.picodotdev.blogbitix.zalandologbook;

...

@Component
public class Beans {

    @Bean
    HttpLogFormatter buildHttpLogFormatter() {
        return new CustomHttpLogFormatter();
    }

    ...

    @Bean("logbookOkHttpClient")
    OkHttpClient buildLogbookOkHttpClient(Logbook logbook) {
        return new OkHttpClient.Builder()
                   .addNetworkInterceptor(new LogbookInterceptor(logbook))
                   .addNetworkInterceptor(new GzipInterceptor())
                   .build();
    }
}
