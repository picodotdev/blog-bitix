public static void bind(ServiceBinder binder) {
	binder.bind(ValidationDecoratorFactory.class, AppValidationDecoratorFactory.class).withId("AppValidationDecoratorFactory");

	binder.bind(ProductoDAO.class, ProductoDAOImpl.class);
}

public static void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration) {
	configuration.add("AppConfigurer", new BeanValidatorConfigurer() {
		public void configure(javax.validation.Configuration<?> configuration) {
			configuration.ignoreXmlConfiguration();
		}
	});
}

/**
 * Los servicios con una interfaz *DAO (es necesario que sea una interfaz)
 * soportan las anotaciones de transaccionalidad.
 */
@Match("*DAO")
public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
	advisor.addTransactionCommitAdvice(receiver);
}