package exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount) {
        super("Insufficient funds for amount: " + amount);
    }
}
