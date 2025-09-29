package io.github.picotodev.blogbitix.javawiremock;

...

public class MainTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = buildWireMockServer();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    void test() throws Exception {
        // given
        stubFor(get(urlEqualTo("/message/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "appication/json")
                        .withBody("{\"id\": 1, \"text\": \"Hello World!\"}")));

        // when
        String response = new Main().getMessage(1L);

        // then
        assertEquals("{\"id\": 1, \"text\": \"Hello World!\"}", response);
    }

    private static WireMockServer buildWireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();
        return wireMockServer;
    }
}
