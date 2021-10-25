import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    	String string = "aaa(1+)bbb";
    	String regexpQuoted = String.format("a+%sb+", Pattern.quote("(1+)"));
        System.out.printf("Quoted (regexp: \"%s\", string: \"%s\"): %s%n", regexpQuoted, string, Pattern.matches(regexpQuoted, string));
    }
}