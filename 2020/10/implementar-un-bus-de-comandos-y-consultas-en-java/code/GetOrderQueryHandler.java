package io.github.picodotdev.blogbitix.eventbus.application.order;

...

@Component
public class GetOrderQueryHandler implements QueryHandler<Order,GetOrderQuery> {

    private OrderRepository orderRepository;

    public GetOrderQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order handle(GetOrderQuery query) throws Exception {
        return orderRepository.findById(query.getOrderId());
    }
}
