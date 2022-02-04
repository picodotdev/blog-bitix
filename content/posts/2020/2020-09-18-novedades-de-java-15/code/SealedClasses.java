public abstract sealed class Shape permits Circle, Rectangle, Square {
    ...
}

public Circle extends Shape {
    ...
}

public Rectangle extends Shape {
    ...
}

public Square extends Shape {
    ...
}