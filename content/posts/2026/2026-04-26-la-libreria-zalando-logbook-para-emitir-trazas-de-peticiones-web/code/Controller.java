package io.github.picodotdev.blogbitix.zalandologbook;

...

@RestController
public class Controller {

    private static final Logger logger = LogManager.getLogger();

    private final OkHttpClient okHttpClient;
    private final OkHttpClient logbookOkHttpClient;

    public Controller(@Qualifier("okHttpClient") OkHttpClient okHttpClient, @Qualifier("logbookOkHttpClient") OkHttpClient logbookOkHttpClient) {
        this.okHttpClient = okHttpClient;
        this.logbookOkHttpClient = logbookOkHttpClient;
    }

    @GetMapping("/")
    public String hello() throws Exception {
        try {
            ThreadContext.put("correlation", UUID.randomUUID().toString());

            Request request = new Request.Builder().url("https://duckduckgo.com/").build();
            Call callOkHttpClient = okHttpClient.newCall(request);
            Call callLogbookOkHttpClient = logbookOkHttpClient.newCall(request);

            logger.info("Without Logbook");
            callOkHttpClient.execute();

            logger.info("With Logbook");
            callLogbookOkHttpClient.execute();

            return "Hello World!";
        } finally {
            ThreadContext.clearAll();
        }
    }
}
