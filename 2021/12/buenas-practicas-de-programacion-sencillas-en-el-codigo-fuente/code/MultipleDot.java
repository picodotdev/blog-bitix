public class User {

    private Addresses addresses;

    public Addresses getAddresses() {
        return addresses;
    }
}

public class UserController {

    public void updateUser(User user) {
        user.getAddress().add(address);
    }
}