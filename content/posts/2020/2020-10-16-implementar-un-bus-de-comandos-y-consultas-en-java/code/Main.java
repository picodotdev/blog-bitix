package io.github.picodotdev.blogbitix.eventbus;

...

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private QueryBus queryBus;

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product = productRepository.findAll().stream().findFirst().orElse(null);
        System.out.println("Stock: " + product.getStock());

        OrderId orderId = orderRepository.generateId();
        commandBus.handle(new CreateOrderCommand(orderId, List.of(new Item(product.getId(), product.getPrice(), 2, new BigDecimal("0.21")))));

        Order order = queryBus.handle(new GetOrderQuery(orderId));
        System.out.printf("OrderId: %s, Items: %s%n", orderId, order.getItems().size());

        System.out.println("Stock: " + product.getStock());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
