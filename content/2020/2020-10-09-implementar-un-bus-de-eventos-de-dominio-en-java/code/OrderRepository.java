package io.github.picodotdev.blogbitix.eventbus.domain.purchase;

...

public interface OrderRepository {

    OrderId generateId();

    void save(Order order);

    List<Order> findAll();
}
