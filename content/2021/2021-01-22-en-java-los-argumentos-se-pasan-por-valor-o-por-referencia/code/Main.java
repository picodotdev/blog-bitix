import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

     public static void method(int x, String y, List<String> w, List<String> z) {
         x = 999;
         y = "Hola Mundo!";
         w = List.of("Hola", "mundo", "!");
         z.addAll(List.of("Hola", "mundo", "!"));
    }

    public static void main(String[] args) {
        int a = 1;
        String b = "Hello World!";
        List<String> c = Arrays.asList("Hello", "World", "!");
        List<String> d = new ArrayList(List.of("Hello", "World", "!"));
        
        Main.method(a, b, c, d);
        
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
    }
}
