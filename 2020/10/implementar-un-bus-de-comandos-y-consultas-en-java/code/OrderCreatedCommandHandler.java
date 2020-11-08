package io.github.picodotdev.blogbitix.eventbus.application.inventory;

...

@Component
public class OrderCreatedCommandHandler implements CommandHandler<OrderCreatedCommand> {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private EventBus eventBus;

    public OrderCreatedCommandHandler(ProductRepository productRepository, OrderRepository orderRepository, EventBus eventBus) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(OrderCreatedCommand command) {
        OrderCreated event = command.getEvent();
        OrderId orderId = event.getOrderId();
        Order order = orderRepository.findById(orderId);

        List<ProductId> oversoldProductIds = order.getItems().stream().filter(it -> {
            Product product = productRepository.findById(it.getProductId());
            return !product.hasStock(it.getQuantity());
        }).map(Item::getProductId).collect(Collectors.toList());

        order.getItems().forEach(it -> {
            Product product = productRepository.findById(it.getProductId());
            product.subtractStock(it.getQuantity());
            eventBus.publish(product);
        });

        if (!oversoldProductIds.isEmpty()) {
            eventBus.publish(new OrderOversold(orderId, oversoldProductIds));
        }
    }
}
