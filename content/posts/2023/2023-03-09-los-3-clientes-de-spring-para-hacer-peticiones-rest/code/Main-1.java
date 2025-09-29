package io.github.picdodotdev.blogbitix.springrestclients;

...

@SpringBootApplication
@EnableWebMvc
public class Main implements CommandLineRunner {

	@Override
	public void run(String... args) {
		restTemplate();
		webClient();
		httpInterface();
	}

	private void restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		String message1 = restTemplate.getForObject("http://localhost:8080/message/", String.class);
		String message2 = restTemplate.getForObject("http://localhost:8080/message/{name}", String.class, "picodotdev");

		System.out.println("RestTemplate (message1): " + message1);
		System.out.println("RestTemplate (message2): " + message2);
	}

    ...

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
