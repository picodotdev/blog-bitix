package io.github.picodotdev.blogbitix.javapact;

...

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class)
@Provider("serviceProvider")
@Consumer("serviceConsumer")
@PactFolder("build/pacts")
class ServiceProviderPactTest {

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }
}
