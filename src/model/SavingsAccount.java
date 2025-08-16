package model;

import bank.core.Account;

public class SavingsAccount extends Account {
    private final double interestRatePercent;

    public SavingsAccount(String owner, double initial, double interestRatePercent) {
        super(owner, initial);
        if (interestRatePercent < 0) throw new IllegalArgumentException("Rate must be >= 0");
        this.interestRatePercent = interestRatePercent;
    }

    @Override public String type() { return "SAVINGS"; }

    // Example of polymorphic behaviour: add interest after each deposit
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        super.addInterestPercent(interestRatePercent);
    }
}
