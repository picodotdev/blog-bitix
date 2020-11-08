package io.github.picodotdev.blogbitix.eventbus.application.order;

...

@Component
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {

    private OrderService orderService;

    public CreateOrderCommandHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void handle(CreateOrderCommand command) throws Exception {
        OrderId orderId = command.getOrderId();
        List<Item> items = command.getItems();
        orderService.create(orderId, items);
    }
}
