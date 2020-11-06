package io.github.picodotdev.blogbitix.httpclientlog;

...

public class Main {

    ...

    public void okHttp() throws Exception {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                logRequest(chain.request());
                Response response = chain.proceed(chain.request());
                logResponse(response);
                return response;
            }

            private void logRequest(Request r) throws IOException {
                String headers = r.headers().toMultimap().entrySet().stream().map((e) -> {
                    return String.format("-H \"%s: %s\"", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
                }).collect(Collectors.joining(","));
                System.out.printf("curl -v %s -X %s %s %s%n", HttpClient.Version.HTTP_2, r.method().toUpperCase(), headers, r.url());
            }

            private void logResponse(Response r) throws IOException {
                String headers = r.headers().toMultimap().entrySet().stream().map((e) -> {
                    return String.format("-H \"%s: %s\"", e.getKey(), e.getValue().stream().collect(Collectors.joining(",")));
                }).collect(Collectors.joining(","));
                System.out.printf("%s %s%n", r.code(), headers);
            }
        };

        Request request = new Request.Builder()
                .url("https://www.google.com/")
                .addHeader("User-Agent", "java/1.0")
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        client.newCall(request).execute().close();
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    ...
}
