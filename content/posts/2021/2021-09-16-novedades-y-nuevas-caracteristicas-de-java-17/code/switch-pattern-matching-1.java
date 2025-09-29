Object o = 123L;
String formatted = null;
if (o instanceof Integer i) {
    formatted = String.format("int %d", i);
} else if (o instanceof Long l) {
    formatted = String.format("long %d", l);
} else if (o instanceof Double d) {
    formatted = String.format("double %f", d);
} else if (o instanceof String s) {
    formatted = String.format("String %s", s);
} else {
    formatted = o.toString();
}