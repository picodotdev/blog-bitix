static void testTriangle(Shape s) {
    switch (s) {
        case null -> { break; }
        case Triangle t
        when t.calculateArea() > 100 -> System.out.println("Large triangle");
        default -> System.out.println("A shape, possibly a small triangle");
    }
}