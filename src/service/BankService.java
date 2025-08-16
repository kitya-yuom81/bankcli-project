package bank.service;

import core.Account;
import core.Result;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

import java.util.*;
import java.util.stream.Collectors;

public class BankService {
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private final Set<String> ids = new HashSet<>();

    public Result<String> createAccount(Account account) {
        var id = account.id();
        if (ids.contains(id)) return Result.fail("ID collision! Try again.");
        ids.add(id);
        accounts.put(id, account);
        return Result.success("Account created.", id);
    }

    public Account getAccount(String id) throws AccountNotFoundException {
        var acc = accounts.get(id);
        if (acc == null) throw new AccountNotFoundException(id);
        return acc;
    }

    public Result<Void> deposit(String id, double amount) throws AccountNotFoundException {
        var acc = getAccount(id);
        acc.deposit(amount);
        return Result.success("Deposited " + amount + " into " + id, null);
    }

    public Result<Void> withdraw(String id, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        var acc = getAccount(id);
        try {
            acc.withdraw(amount);
            return Result.success("Withdrew " + amount + " from " + id, null);
        } catch (IllegalArgumentException e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("insufficient"))
                throw new InsufficientFundsException(amount);
            throw e;
        }
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public int count() { return accounts.size(); }

    public double totalBalance() {
        return accounts.values().stream().mapToDouble(Account::balance).sum();
    }

    public Map<String, Long> countByType() {
        return accounts.values().stream()
                .collect(Collectors.groupingBy(Account::type, LinkedHashMap::new, Collectors.counting()));
    }
}
