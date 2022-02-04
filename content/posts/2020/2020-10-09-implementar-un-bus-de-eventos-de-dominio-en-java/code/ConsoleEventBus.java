package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component("ConsoleEventBus")
public class ConsoleEventBus implements EventBus {

    @Override
    public void publish(Event event) {
        System.out.printf("%s %s %s%n", event.getClass().getName(), event.getId().getValue(), event.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
