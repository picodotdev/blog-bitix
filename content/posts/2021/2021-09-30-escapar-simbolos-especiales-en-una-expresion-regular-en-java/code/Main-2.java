import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    	String string = "aaa(1+)bbb";
    	String regexpUnquoted = "a+(1+)b+";
        System.out.printf("Unquoted (regexp: \"%s\", string: \"%s\"): %s%n", regexpUnquoted, string, Pattern.matches(regexpUnquoted, string));
    }
}