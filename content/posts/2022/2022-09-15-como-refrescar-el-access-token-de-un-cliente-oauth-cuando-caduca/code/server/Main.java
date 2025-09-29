package io.github.picodotdev.blogbitix.springbootjaxrsoauth.server;

...

@SpringBootApplication
@EnableWebSecurity
public class Main {

	@Bean
	MessageService messageService() {
		return new DefaultMessageService();
	}

	@Bean
	JwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri) {
		NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);

		OAuth2TokenValidator<Jwt> issuerValidator = JwtValidators.createDefaultWithIssuer(issuerUri);
		OAuth2TokenValidator<Jwt> audienceValidator = new JwtClaimValidator<List<String>>(AUD, aud -> aud.contains("spring-boot-client"));
		OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(issuerValidator, audienceValidator);

		jwtDecoder.setJwtValidator(validator);

		return jwtDecoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
