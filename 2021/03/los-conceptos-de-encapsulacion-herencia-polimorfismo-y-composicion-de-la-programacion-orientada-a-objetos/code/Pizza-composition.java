public class Pizza {

    private String base;
    private List<String> ingredients;
    private BakeStyle bakeStyle;

    public Pizza(String base, List<String> ingredients, BakeStyle bakeStyle) {
        ...
    }

    public void bake() {
        bakeStyle.bake(this);
    }
}

public class CarbonaraPizza extends Pizza {

    public CarbonaraPizza(BakeStyle bakeStyle) {
        super("Normal", List.of("Mozzarella Cheese", "Bacon", "Mushroom", "Onion"), bakeStyle);
    }
}

public class BarbecuePizza extends Pizza  {

    public BarbecuePizza(BakeStyle bakeStyle) {
        super("Normal", List.of("Mozzarella Cheese", "Beef", "Onion", "Bacon", "Corn"), bakeStyle);
    }
}

public class HawaianPizza extends Pizza {

    public HawaianPizza(BakeStyle bakeStyle) {
        super("Normal", List.of("Mozzarella Cheese Extra", "York Double", "Pineapple"), bakeStyle);
    }
}

public abstract BakeStyle {
    public abstract void bake(Pizza pizza);
}

public NewYorkBakeStyle {
    public void bake(Pizza pizza) {
        ...
    }
}

public ChicagoNewYorkBakeStyle {
    public void bake(Pizza pizza) {
        ...
    }
}