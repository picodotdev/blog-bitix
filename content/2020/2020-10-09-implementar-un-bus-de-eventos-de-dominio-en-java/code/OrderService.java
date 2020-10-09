package io.github.picodotdev.blogbitix.eventbus.domain.purchase;

...

public class OrderService {

    private OrderRepository orderRepository;
    private EventBus eventBus;

    public OrderService(OrderRepository orderRepository, DomainEventBus eventBus) {
        this.orderRepository = orderRepository;
        this.eventBus = eventBus;
    }

    public OrderId generateId() {
        return orderRepository.generateId();
    }

    public void create(OrderId id, List<Item> items) throws Exception {
        Order order = Order.create(id, items);

        if (isValidAmount(order)) {
            throw new Exception("Invalid order amount");
        }

        orderRepository.save(order);
        eventBus.publish(order);
    }

    private boolean isValidAmount(Order order) {
        return order.getAmount().compareTo(new BigDecimal("3000.00")) == -1;
    }
}
