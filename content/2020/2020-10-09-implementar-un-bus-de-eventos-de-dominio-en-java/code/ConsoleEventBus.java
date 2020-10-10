package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component("ConsoleEventBus")
@Primary
public class ConsoleEventBus implements EventBus {

    @Override
    public void publish(Event e) {
        System.out.printf("%s %s %s%n", e.getClass().getName(), e.getId().getValue(), e.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}