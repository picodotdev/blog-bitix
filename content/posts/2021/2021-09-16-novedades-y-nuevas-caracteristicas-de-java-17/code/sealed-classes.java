public abstract sealed class Shape permits Circle, Rectangle, Square { ... }

public class Circle extends Shape { ... }
public class Rectangle extends Shape { ... }
public class Square extends Shape { ... }