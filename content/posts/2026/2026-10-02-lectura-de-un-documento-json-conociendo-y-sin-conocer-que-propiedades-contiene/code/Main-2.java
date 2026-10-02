package io.github.picodotdev.blogbitix.javajsonread;

public class Main {

    public static void main(String[] args) {
        ...
    
        {
            System.out.println("Jackson (map)");
            ObjectMapper mapper = new ObjectMapper();
            InputStream stream = Main.class.getResourceAsStream("/data.json");
            Map<String, Object> map = mapper.readValue(stream, new TypeReference<>() {});

            System.out.println(map);

            System.out.println();

            System.out.println("City 1: " + map.get("city"));

            map.entrySet().stream().filter(e -> !(e.getValue() instanceof Map)).forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));

            System.out.println();

            ((Map<String, Object>) map.get("other")).forEach((key, value) -> System.out.println(key + " = " + value));
        }

        ...
    }
}
