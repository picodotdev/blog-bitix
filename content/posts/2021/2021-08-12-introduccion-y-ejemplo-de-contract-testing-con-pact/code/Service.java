package io.github.picodotdev.blogbitix.javapact;

...

public interface Service {

    @GET("/message")
    Call<String> message(@Header("Accept-Language") String acceptLanguage, @Query("random") String random);

    @GET("/message/{name}")
    Call<String> message(@Path("name") String name, @Header("Accept-Language") String acceptLanguage, @Query("random") String random);
}
