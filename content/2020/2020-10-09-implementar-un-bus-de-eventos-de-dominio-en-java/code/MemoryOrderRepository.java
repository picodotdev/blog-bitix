package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

public class MemoryOrderRepository implements OrderRepository {

    private List<Order> orders;

    public MemoryOrderRepository() {
        this.orders = new ArrayList<>();
    }

    @Override
    public OrderId generateId() {
        return new OrderId(UUID.randomUUID());
    }

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
