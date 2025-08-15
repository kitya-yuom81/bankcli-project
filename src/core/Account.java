package core;

import java.util.UUID;

public abstract class Account  {
    private final String id;
    private final String owner;
    private double balance;

    protected Account(String owner, double initial) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner cannot be null or empty");
        }
        if (initial < 0) {
            throw new IllegalArgumentException("initial cannot be negative");
        }
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.owner = owner;
        this.balance = initial;
    }

    public String getId() {
        return id;
    }
    public String getOwner() {
        return owner;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit cannot be negative");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw cannot be negative");
        }
        balance -= amount;
    }
    public abstract String type();

    public String details() {
        return String.format("[%s] %s | Owner=%s | Balance: %.2f", id, type(), owner, balance);
    }

}
