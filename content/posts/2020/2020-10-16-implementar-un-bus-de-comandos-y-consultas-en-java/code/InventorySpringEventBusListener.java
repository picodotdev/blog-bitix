package io.github.picodotdev.blogbitix.eventbus.application.inventory;

...

@Component
public class InventorySpringEventBusListener {

    private CommandBus commandBus;

    private InventorySpringEventBusListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @EventListener
    public void onOrderCreated(OrderCreated orderCreated) throws Exception {
        OrderCreatedCommand command = new OrderCreatedCommand(orderCreated);
        commandBus.handle(command);
    }
}
