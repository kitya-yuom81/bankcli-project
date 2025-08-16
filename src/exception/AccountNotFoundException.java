package exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String id) {
        super("Account not found: " + id);
    }
}
