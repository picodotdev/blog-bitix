public interface Math {

    BigDecimal mutiply(BigDecimal a, BigDecimal b);

    default BigDecimal square(BigDecimal a) {
        return mutiply(a, a);
    }

    default BigDecimal cube(BigDecimal a) {
        return mutiply(square(a), a);
    }
}