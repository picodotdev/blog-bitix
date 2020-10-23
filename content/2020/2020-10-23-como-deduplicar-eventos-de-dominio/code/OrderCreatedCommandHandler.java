package io.github.picodotdev.blogbitix.eventbus.application.inventory;

...

@Component
public class OrderCreatedCommandHandler implements CommandHandler<OrderCreatedCommand> {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private EventRepository eventRepository;
    private EventBus eventBus;

    public OrderCreatedCommandHandler(ProductRepository productRepository, OrderRepository orderRepository, EventRepository eventRepository, EventBus eventBus) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(OrderCreatedCommand command) {
        OrderCreated event = command.getEvent();

        if (eventRepository.exists(event)) {
            System.out.printf("Duplicated event %s%n", event.getId().getValue());
            return;
        }
        
        ...

        eventRepository.add(event);
    }
}
