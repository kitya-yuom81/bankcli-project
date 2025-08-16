package core;

public interface Transactional {
    void deposit(double amount);
    void withdraw(double amount) throws IllegalArgumentException;
}
