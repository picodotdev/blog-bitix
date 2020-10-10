package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component("SpringEventBus")
public class SpringEventBus implements EventBus {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
