package io.github.picodotdev.blogbitix.graphql;

...

public class LocalDateCoercing implements Coercing<LocalDate, String> {

    private DateTimeFormatter formatter;

    public LocalDateCoercing() {
        this(DateTimeFormatter.ISO_DATE);
    }

    public LocalDateCoercing(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String serialize(Object dataFetcherResult) {
        try {
            LocalDate date = (LocalDate) dataFetcherResult;
            return date.format(formatter);
        } catch (Exception e) {
            throw new CoercingSerializeException(e);
        }
    }

    @Override
    public LocalDate parseValue(Object input) {
        return parse(input);
    }

    @Override
    public LocalDate parseLiteral(Object input) {
        return parse(input);
    }

    private LocalDate parse(Object input) {
        try {
            String string = (String) input;
            return LocalDate.parse(string, formatter);
        } catch (Exception e) {
            throw new CoercingParseValueException(e);
        }
    }
}
