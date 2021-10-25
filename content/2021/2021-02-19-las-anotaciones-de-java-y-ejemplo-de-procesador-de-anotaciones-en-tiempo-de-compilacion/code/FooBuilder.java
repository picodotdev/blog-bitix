package io.github.picodotdev.blogbitix.javaannotations;

public class FooBuilder {

    private java.lang.String name;
    private java.util.Optional<java.lang.String> color;

    public FooBuilder name(java.lang.String name) {
        this.name = name;
        return this;
    }
    public FooBuilder color(java.util.Optional<java.lang.String> color) {
        this.color = color;
        return this;
    }

    public Foo build() {
        return new Foo(name, color);
    }
}
