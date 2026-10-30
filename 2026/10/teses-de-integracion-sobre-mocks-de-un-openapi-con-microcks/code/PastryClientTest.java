package io.github.picodotdev.blogbitix.microcks;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import io.github.microcks.testcontainers.MicrocksContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class PastryClientTest {

    @Container
    static MicrocksContainer microcks = new MicrocksContainer(DockerImageName.parse("quay.io/microcks/microcks-uber:1.11.0"))
                                                .withMainArtifacts("pastry-openapi.yaml");

    @DynamicPropertySource
    static void apiProperties(DynamicPropertyRegistry registry) {
        registry.add("pastry.api.base-url", () -> microcks.getRestMockEndpoint("API Pastries", "0.0.1"));
    }

    @Autowired
    PastryClient client;

    @Test
    void shouldFindPastryByName() {
        Pastry pastry = client.findByName("Millefeuille");

        assertThat(pastry.name()).isEqualTo("Millefeuille");
        assertThat(pastry.size()).isEqualTo("L");
        assertThat(pastry.price()).isEqualByComparingTo(new BigDecimal("4.40"));
        assertThat(pastry.status()).isEqualTo("available");
    }
}
