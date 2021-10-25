package io.github.picotodev.blogbitix.javawiremock;

...

public class Main {

    private Service service;

    public Main() {
        this.service = buildService();
    }

    public String getMessage(Long id) throws IOException {
        return service.message(id).execute().body();
    }

    public Service buildService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://localhost:8080/").build();

        return retrofit.create(Service.class);
    }

    public static void main(String[] args) {
    }
}
