package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component
public class MemoryOrderRepository implements OrderRepository {

    private Map<OrderId, Order> orders;

    public MemoryOrderRepository() {
        this.orders = new HashMap<>();
    }

    @Override
    public OrderId generateId() {
        return new OrderId(UUID.randomUUID());
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(OrderId id) {
        return orders.get(id);
    }

    @Override
    public Collection<Order> findAll() {
        return orders.values();
    }
}