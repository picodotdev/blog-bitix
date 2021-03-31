public class Pizza {

    private String base;
    private List<String> ingredients;

    public Pizza(String base, List<String> ingredients) {
        ...
    }

}

public class CarbonaraPizza extends Pizza {

    public CarbonaraPizza() {
        super("Normal", List.of("Mozzarella Cheese", "Bacon", "Mushroom", "Onion"));
    }
}

public class BarbecuePizza extends Pizza  {

    public BarbecuePizza() {
        super("Normal", List.of("Mozzarella Cheese", "Beef", "Onion", "Bacon", "Corn"));
    }
}

public class HawaianPizza extends Pizza {

    public HawaianPizza() {
        super("Normal", List.of("Mozzarella Cheese Extra", "York Double", "Pineapple"));
    }
}