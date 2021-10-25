@Contribute(ComponentClassTransformWorker2.class)
public static void contributeWorkers(OrderedConfiguration<ComponentClassTransformWorker2> configuration) {
	configuration.addInstance("CSRF", CsrfWorker.class);
}