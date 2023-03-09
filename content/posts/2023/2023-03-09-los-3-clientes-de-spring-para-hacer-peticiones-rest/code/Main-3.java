package io.github.picdodotdev.blogbitix.springrestclients;

...

@SpringBootApplication
@EnableWebMvc
public class Main implements CommandLineRunner {

	...

	private void httpInterface() {
		WebClient client = WebClient.builder().clientConnector(new JdkClientHttpConnector()).baseUrl("http://localhost:8080").build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

		MessageService messageService = factory.createClient(MessageService.class);
		String message1 = messageService.message();
		String message2 = messageService.message("picodotdev");

		System.out.println("RestTemplate (message1): " + message1);
		System.out.println("RestTemplate (message2): " + message2);
	}

	...
}
