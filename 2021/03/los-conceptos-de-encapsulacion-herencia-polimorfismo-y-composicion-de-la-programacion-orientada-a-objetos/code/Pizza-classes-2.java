public class Pizza {

    private String base;
    private List<String> ingredients;

    public Pizza(String base, List<String> ingredients) {
        ...
    }

    ...
}

public class CarbonaraPizza extends Pizza {

    public CarbonaraPizza() {
        super("Normal", List.of("Mozzarella Cheese", "Bacon", "Mushroom", "Onion"));
    }

    ...
}

public class BarbecuePizza extends Pizza  {

    public BarbecuePizza() {
        super("Normal", List.of("Mozzarella Cheese", "Beef", "Onion", "Bacon", "Corn"));
    }

    ...
}

public class HawaianPizza extends Pizza {

    public HawaianPizza() {
        super("Normal", List.of("Mozzarella Cheese Extra", "York Double", "Pineapple"));
    }

    ...
}

public class NewYorkStyleCarbonaraPizza extends HawaianPizza {
    ...
}

public class ChicagoStyleCarbonaraPizza extends HawaianPizza {
    ...
}

public class NewYorkStyleBarbecuePizza extends HawaianPizza {
    ...
}

public class ChicagoStyleBarbecuePizza extends HawaianPizza {
    ...
}

public class NewYorkStyleHawawianPizza extends HawaianPizza {
    ...
}

public class ChicagoStyleHawawianPizza extends HawaianPizza {
    ...
}