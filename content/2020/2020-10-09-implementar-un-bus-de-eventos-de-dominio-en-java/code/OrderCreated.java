package io.github.picodotdev.blogbitix.eventbus.domain.order;

...

public class OrderCreated extends Event {

    private OrderId orderId;

    public OrderCreated(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}