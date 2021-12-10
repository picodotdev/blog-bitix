public class User {

    private Addresses addresses;

    public Addresses addAddress(Address address) {
        return addresses.add(address);
    }
}

public class UserController {

    public void updateUser(User user) {
        user.addAddress(address);
    }
}