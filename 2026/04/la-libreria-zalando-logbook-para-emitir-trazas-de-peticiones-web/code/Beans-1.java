package io.github.picodotdev.blogbitix.zalandologbook;

...

@Component
public class Beans {

    @Bean
    HttpLogFormatter buildHttpLogFormatter() {
        return new CustomHttpLogFormatter();
    }

    @Bean("okHttpClient")
    OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                   .addInterceptor(logging)
                   .build();
    }

    ...
}
