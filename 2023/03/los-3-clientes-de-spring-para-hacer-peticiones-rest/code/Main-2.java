package io.github.picdodotdev.blogbitix.springrestclients;

...

@SpringBootApplication
@EnableWebMvc
public class Main implements CommandLineRunner {

	...

	private void webClient() {
		ExchangeFilterFunction filter = (ClientRequest request, ExchangeFunction next) -> {
			System.out.println("Filter " + request.url().getPath());
			return next.exchange(request);
		};
		WebClient webClient = WebClient.builder().clientConnector(new JdkClientHttpConnector()).filter(filter).baseUrl("http://localhost:8080").build();

		String message1 = webClient.get().uri("/message/").retrieve().bodyToMono(String.class).block();
		String message2 = webClient.get().uri("/message/{name}",  "picodotdev").retrieve().bodyToMono(String.class).block();

		System.out.println("WebClient (message1): " + message1);
		System.out.println("WebClient (message2): " + message2);
	}

	...

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
