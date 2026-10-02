package io.github.picodotdev.blogbitix.javajsonread;

public class Main {

    public static void main(String[] args) {
        {
            System.out.println("Jackson");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(Main.class.getResourceAsStream("/data.json"));

            String city1 = root.get("city").asString();
            String city2 = root.at("/city").asString();
            System.out.println("City 1: " + city1);
            System.out.println("City 2: " + city2);

            root.propertyStream().filter(p -> p.getValue().isValueNode()).forEach(p -> System.out.println(p.getKey() + " = " + p.getValue().asString()));

            System.out.println();

            root.at("/other").properties().forEach(p -> System.out.println(p.getKey() + " = " + p.getValue().asString()));
        }

        ...
    }
}
