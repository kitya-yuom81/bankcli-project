package model;

import bank.core.Account;

public class CheckingAccount extends Account {
    private final double txFee;

    public CheckingAccount(String owner, double initial, double txFee) {
        super(owner, initial);
        if (txFee < 0) throw new IllegalArgumentException("Fee must be >= 0");
        this.txFee = txFee;
    }

    @Override public String type() { return "CHECKING"; }

    // Polymorphic behaviour: charge a small fee per withdrawal
    @Override
    public void withdraw(double amount) throws IllegalArgumentException {
        super.withdraw(amount);
        super.addFee(txFee);
    }
}
