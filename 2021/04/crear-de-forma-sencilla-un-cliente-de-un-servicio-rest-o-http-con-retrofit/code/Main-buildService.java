public class Main implements CommandLineRunner {

    @Autowired
    private MeterRegistry registry;

    ...

    private Service buildService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(buildLoggingInterceptor())
                .eventListener(OkHttpMetricsEventListener.builder(registry, "okhttp.requests").build())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://localhost:8080/").build();

        return retrofit.create(Service.class);
    }

    ...
}