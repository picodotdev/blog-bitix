package io.github.picotodev.blogbitix.javawiremock;

...

public interface Service {

    @GET("/message/{id}")
    Call<String> message(@Path("id") Long id);
}
