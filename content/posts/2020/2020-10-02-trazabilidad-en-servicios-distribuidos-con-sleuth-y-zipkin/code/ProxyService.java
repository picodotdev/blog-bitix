package io.github.picodotdev.blogbitix.springcloud.client;

...

@Component
public class ProxyService {

    ...

    @Autowired
    private Tracing tracing;

    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    ...
    private HttpClient client;

    public ProxyService() {
        ...

        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }

    public String get() {
        ServiceInstance instance = loadBalancer.choose("traefik");
        URI uri = instance.getUri();
        String resource = String.format("%s%s", uri.toString().replace("127.0.0.1", "traefik"), "/service");
        final URI resourceUri = URI.create(resource);

        ...

        Supplier<CompletableFuture<String>> get = () -> {
            return CompletableFuture.supplyAsync(() -> {
                Span span = tracer.newTrace().kind(Span.Kind.CLIENT).name("CLIENT").start();
                System.out.printf("Client Span (traceId: %s, spanId: %s)%n", span.context().traceIdString(), span.context().spanIdString());

                String result = getRequest(client, span, resourceUri);
                //String result = getRequest(restTemplate, span, resouceUri);

                span.finish();
                return result;
            });
        };
        ...

        return Try.of(getCircuitBreaker::call).recover((throwable) -> getFallback()).get();
    }

    private String getFallback() {
        return "Fallback";
    }

    private String getRequest(HttpClient client, Span span, URI resourceUri) {
        HttpRequest.Builder request = HttpRequest.newBuilder(resourceUri).GET();
        Span serviceSpan = tracer.newChild(span.context());

        try (Tracer.SpanInScope ws = tracer.withSpanInScope(serviceSpan)) {
            TraceContext.Injector<HttpRequest.Builder> injector = tracing.propagation().injector((HttpRequest.Builder carrier, String key, String value) -> {
                carrier.header(key, value);
            });
            injector.inject(tracer.currentSpan().context(), request);

            HttpResponse<String> response = client.send(request.build(), HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return getFallback();
        } finally {
            serviceSpan.finish();
        }
    }

    private String getRequest(RestTemplate restTemplate, Span span, URI resourceUri) {
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(span)) {
            return restTemplate.getForObject(resourceUri, String.class);
        } catch (RestClientException e) {
            return getFallback();
        }
    }
}
