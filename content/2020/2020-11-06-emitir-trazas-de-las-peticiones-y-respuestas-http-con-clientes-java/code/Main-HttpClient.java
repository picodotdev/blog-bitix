package io.github.picodotdev.blogbitix.httpclientlog;

...

public class Main {

    ...

    public void httpClient() throws Exception {
        Function<HttpRequest, String> onRequest = (HttpRequest r) -> {
            String headers = r.headers().map().entrySet().stream().map((e) -> {
                return String.format("-H \"%s: %s\"", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
            }).collect(Collectors.joining(","));
            return String.format("curl -v %s -X %s %s %s", VERSION.get(r.version().orElse(HttpClient.Version.HTTP_2)), r.method().toUpperCase(), headers, r.uri());
        };
        BiConsumer<HttpResponse<?>, String> onResponse = (HttpResponse<?> r, String curl) -> {
            String headers = r.headers().map().entrySet().stream().map((e) -> {
                return String.format("[%s: %s]", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
            }).collect(Collectors.joining(","));
            System.out.printf("%s%n", curl);
            System.out.printf("%s %s%n", r.statusCode(), headers);
        };
        BiConsumer<Throwable, String> onError = (Throwable t, String curl) -> {
            System.out.printf("%s%n", curl);
            t.printStackTrace();
        };

        HttpClient client = InterceptableHttpClient.builder().version(HttpClient.Version.HTTP_2).interceptor(onRequest, onResponse, onError).build();

        HttpResponse<String> response = client.send(HttpRequest.newBuilder(new URI("https://www.google.es/")).headers("User-Agent", "java/1.0").GET().build(), HttpResponse.BodyHandlers.ofString());
    }

    ...
}
