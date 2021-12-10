public class User{

    private Email email;

    public User(Email email) {
        this.email = email;
    }
}

public class Email {

    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        if (!isValid(value)) {
            throw new Exception();
        }
        return new Email(value);
    }

    public static boolean isValid(String value) {
        ...
    }
}