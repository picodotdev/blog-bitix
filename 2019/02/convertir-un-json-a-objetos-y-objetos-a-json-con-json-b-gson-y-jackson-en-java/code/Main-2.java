public class Main {

    public static void main(String[] args) throws Exception {
        Comprador comprador = buildComprador();
        ...

        // JSON-B
        compradores = jsonb.fromJson(arrayJson, new ArrayList<Comprador>(){}.getClass().getGenericSuperclass());

        // Gson
        compradores = gson.fromJson(arrayJson, new TypeToken<List<Comprador>>(){}.getType());

        // Jackson
        compradores = mapper.readValue(arrayJson, new TypeReference<List<Comprador>>(){});
        
        ...
    }

    ...
}