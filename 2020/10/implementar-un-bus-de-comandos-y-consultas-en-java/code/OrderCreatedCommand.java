package io.github.picodotdev.blogbitix.eventbus.application.inventory;

...

public class OrderCreatedCommand extends Command {

    private OrderCreated event;

    public OrderCreatedCommand(OrderCreated event) {
        this.event = event;
    }

    public OrderCreated getEvent() {
        return event;
    }
}
