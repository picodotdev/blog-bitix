public class Account {

    private Money balance;

    public void add(Money amount) {
        return balance.add(amount);
    }
}

public AccountController {

    public void updateBalance(Account account, Money amount) {
        account.add(amount);
    }
}