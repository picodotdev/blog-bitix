package io.github.picodotdev.blogbitix.log4j;

import org.apache.logging.log4j.message.Message;

public class ProductMessage implements Message {

    private Product product;

    public ProductMessage(Product product) {
        this.product = product;
    }

    @Override
    public String getFormat() {
        return "Product(%d, %s, %s)";
    }

    @Override
    public Object[] getParameters() {
        return new Object[] { product.getId(), product.getNombre(), product.getColor() };
    }

    @Override
    public String getFormattedMessage() {
        return String.format(getFormat(), getParameters());
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }
}
