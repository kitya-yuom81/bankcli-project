package core;

import java.util.UUID;

public abstract class Account implements Transactional {
    private final String id;
    private final String owner;
    private double balance;

    protected Account(String owner, double initial) {
        if (owner == null || owner.isBlank()) throw new IllegalArgumentException("Owner required");
        if (initial < 0) throw new IllegalArgumentException("Initial must be >= 0");
        this.id = UUID.randomUUID().toString().substring(0, 8); // short id
        this.owner = owner;
        this.balance = initial;
    }

    public final String id() { return id; }
    public final String owner() { return owner; }
    public final double balance() { return balance; }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
    }

    public abstract String type();

    public String details() {
        return String.format("[%s] %s | Owner=%s | Balance=%.2f",
                id, type(), owner, balance);
    }

    protected void addFee(double fee) {
        if (fee < 0) return;
        if (fee > balance) balance = 0;
        else balance -= fee;
    }

    protected void addInterestPercent(double ratePercent) {
        if (ratePercent <= 0) return;
        balance += balance * (ratePercent / 100.0);
    }
}
