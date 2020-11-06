package io.github.picodotdev.blogbitix.httpclientlog;

...

public class Main {

    ...

    public void webClient() {
        ExchangeFilterFunction logRequest = ExchangeFilterFunction.ofRequestProcessor(r -> {
            String headers = r.headers().entrySet().stream().map((e) -> {
                return String.format("-H \"%s: %s\"", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
            }).collect(Collectors.joining(","));
            System.out.printf("curl -v %s -X %s %s %s%n", HttpClient.Version.HTTP_2, r.method().name().toUpperCase(), headers, r.url());
            return Mono.just(r);
        });

        ExchangeFilterFunction logRespose = ExchangeFilterFunction.ofResponseProcessor(r -> {
            String headers = r.headers().asHttpHeaders().entrySet().stream().map((e) -> {
                return String.format("-H \"%s: %s\"", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
            }).collect(Collectors.joining(","));
            System.out.printf("%s %s%n", r.statusCode(), headers);
            return Mono.just(r);
        });

        WebClient client = WebClient.builder().filters(f -> {
            f.add(logRequest);
            f.add(logRespose);
        }).baseUrl("https://www.google.com/").build();
        client.get().uri("/").header("User-Agent", "java/1.0").retrieve().toEntity(String.class).block();
    }

    ...
}
