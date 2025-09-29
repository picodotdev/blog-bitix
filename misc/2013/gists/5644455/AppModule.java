public static void contributeWebSecurityManager(Configuration<Realm> configuration) {
	ExtendedPropertiesRealm realm = new ExtendedPropertiesRealm("classpath:shiro-users.properties");
	configuration.add(realm);
}

public static void contributeSecurityConfiguration(Configuration<SecurityFilterChain> configuration, SecurityFilterChainFactory factory) {
	configuration.add(factory.createChainWithRegEx("^*/login*$").add(factory.anon()).build());
	configuration.add(factory.createChainWithRegEx("^*/index*$").add(factory.user()).build());
}