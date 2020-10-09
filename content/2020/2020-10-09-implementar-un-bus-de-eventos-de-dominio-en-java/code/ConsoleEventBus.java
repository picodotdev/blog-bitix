package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

public class ConsoleEventBus implements DomainEventBus {

    @Override
    public void publish(DomainEvent e) {
        System.out.printf("%s %s %s%n", e.getClass().getName(), e.getId().getValue(), e.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
