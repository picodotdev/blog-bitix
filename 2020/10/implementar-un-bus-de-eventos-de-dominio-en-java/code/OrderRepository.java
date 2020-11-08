package io.github.picodotdev.blogbitix.eventbus.domain.order;

...

public interface OrderRepository {

    OrderId generateId();

    void save(Order order);

    Order findById(OrderId id);
    Collection<Order> findAll();
}