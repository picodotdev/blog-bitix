package io.github.picodotdev.blogbitix.javajsonread;

...

public class Main {

    public static void main(String[] args) {
        ...

        {
            System.out.println("Jakarta JSON");
            JsonReader reader = Json.createReader(Main.class.getResourceAsStream("/data.json"));
            JsonStructure root = reader.read();

            JsonValue city1 = root.asJsonObject().get("city");
            JsonValue city2 = Json.createPointer("/city").getValue(root);
            System.out.println("City: " + city1);
            System.out.println("City: " + city2);

            root.asJsonObject().entrySet().stream()
                .filter(e -> List.of(ValueType.NULL, ValueType.STRING, ValueType.NUMBER, ValueType.TRUE, ValueType.FALSE).contains(e.getValue().getValueType()))
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue().toString()));

            System.out.println();

            Json.createPointer("/other").getValue(root).asJsonObject().forEach((key, value) -> System.out.println(key + " = " + value.toString()));
        }
    }
}
