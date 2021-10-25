package io.github.picodotdev.blogbitix.springcloud.service;

...

@RestController
public class DefaultController {

    @Autowired
    private DefaultConfiguration configuration;

    @Autowired
    private Tracing tracing;

    @Autowired
    private Tracer tracer;

    private Random random;
    private Counter counter;

    ...

    @RequestMapping("/")
    public String home(HttpServletRequest request) throws Exception {
        Span span = tracer.currentSpan();

        System.out.printf("Service Span (traceId: %s, spanId: %s)%n", span.context().traceIdString(), span.context().spanIdString());
        counter.increment();

        // Timeout simulation
        //Thread.sleep(random.nextInt(4000));

        return String.format("Hello world (url: %s, remoteAddress_%s, localAddress: %s, traceId: %s, spanId: %s, key: %s)", request.getRequestURL(),
                request.getRemoteAddr(), request.getLocalAddr(), span.context().traceIdString(), span.context().spanIdString(), configuration.getKey());
    }
}
