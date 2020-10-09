package io.github.picodotdev.blogbitix.eventbus;

...

public class Main {

    public static void main(String[] args) throws Exception {
        ConsoleEventBus eventBus = new ConsoleEventBus();
        MemoryOrderRepository orderRepository = new MemoryOrderRepository();
        OrderService orderService = new OrderService(orderRepository, eventBus);

        Product product = new Product(new BigDecimal("10.0"), 5);

        orderService.create(orderService.generateId(), List.of(new Item(product, 2, new BigDecimal("0.21"))));
    }
}
