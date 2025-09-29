package io.github.picodotdev.blogbitix.eventbus.application.order;

...

public class GetOrderQuery extends Query<Order> {

    private OrderId orderId;

    public GetOrderQuery(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
