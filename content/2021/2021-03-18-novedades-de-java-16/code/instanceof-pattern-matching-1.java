// Antes
if (obj instanceof String) {
   String s = (String) obj;
   System.out.println(s);
}

// Ahora, con pattern matching
if (obj instanceof String s) {
   System.out.println(s);
}
if (obj instanceof String s && s.contains("jdk")) {
   System.out.println(s);
}