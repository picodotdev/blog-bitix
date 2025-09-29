package es.com.blogspot.elblogdepicodev.plugintapestry.services;

...

public class AppModule {

	public static void bind(ServiceBinder binder) {
		// Añadir al contenedor de dependencias nuestros servicios, se proporciona la interfaz y la
		// implementación. Si tuviera un constructor con parámetros se inyectarían como
		// dependencias.
		// binder.bind(Sevicio.class, ServicioImpl.class);

		// Servicios de persistencia (definidos en Spring por la necesidad de que Spring gestione las transacciones)
		// binder.bind(ProductoDAO.class, ProductoDAOImpl.class);
	}

	// Servicio que delega en Spring la inicialización de Hibernate, solo obtiene la configuración de Hibernate creada por Spring
	public static HibernateSessionSource buildAppHibernateSessionSource(ApplicationContext context) {
		return new HibernateSessionSourceImpl(context);
	}

	public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration, @Local HibernateSessionSource hibernateSessionSource) {
		configuration.add(HibernateSessionSource.class, hibernateSessionSource);
	}

	...

	public static void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration) {
		configuration.add("AppConfigurer", new BeanValidatorConfigurer() {
			public void configure(javax.validation.Configuration<?> configuration) {
				configuration.ignoreXmlConfiguration();
			}
		});
	}

	...
}