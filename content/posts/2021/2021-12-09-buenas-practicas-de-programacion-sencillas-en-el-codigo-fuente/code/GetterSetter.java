public class Account {

    private Money balance;

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}

public AccountController {

    public void updateBalance(Account account, Money amount) {
        account.setBalance(account.getBalance().add(amount));
    }
}