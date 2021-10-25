package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component("SpringEventBus")
@Primary
public class SpringEventBus implements EventBus {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Event event) {
        System.out.printf("%s %s %s%n", event.getClass().getName(), event.getId().getValue(), event.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
        applicationEventPublisher.publishEvent(event);
        System.out.printf("%s %s %s%n", event.getClass().getName(), event.getId().getValue(), event.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
        applicationEventPublisher.publishEvent(event);
    }
}
