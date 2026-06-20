@Bean
public Consumer<ErrorMessage> customErrorHandler(){
    return errorMessage -> {
        logger.error(...);
    };
}