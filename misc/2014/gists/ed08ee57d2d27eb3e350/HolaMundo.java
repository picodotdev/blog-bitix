package io.github.picodotdev.java8.holaMundo;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HolaMundo {

	public static void main(String[] args) {
		Function<String, String> capitalize = (String s) -> Character.toUpperCase(s.charAt(0)) + s.substring(1);
		String msg = Arrays.asList("¡", "hola", " ", "mundo", "!").stream().map(capitalize).collect(Collectors.joining());
		System.out.println(msg);
	}
}