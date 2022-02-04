package io.github.picodotdev.blogbitix.dddhexagonal.catalog.infrastructure.spring;

...

@Component
@Primary
public class SpringEventBus implements DomainEventBus {

    public void publish(DomainEvent event) {
        System.out.printf("%s %s %s%n", event.getClass().getName(), event.getId().getValue(), event.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}