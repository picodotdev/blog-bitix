package io.github.picodotdev.blogbitix.microcks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PastryClient {

    private final RestClient restClient;

    public PastryClient(RestClient.Builder builder, @Value("${pastry.api.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public Pastry findByName(String name) {
        return restClient.get()
                         .uri("/pastries/{name}", name)
                         .retrieve()
                         .body(Pastry.class);
    }
}
