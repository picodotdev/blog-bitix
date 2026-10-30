package io.github.picodotdev.blogbitix.microcks;

import java.math.BigDecimal;

public record Pastry(String name, String description, String size, BigDecimal price, String status) {
}
