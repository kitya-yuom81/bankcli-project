import core.Result;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import model.CheckingAccount;
import model.SavingsAccount;
import service.BankService;
import util.Input;
import util.Printer;

public class Main {
    private static final BankService service = new BankService();

    public static void main(String[] args) {
        System.out.println("=== Mini Banking CLI ===");
        var input = new Input(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.nextInt("Choose (1-7): ");
            switch (choice) {
                case 1 -> createAccount(input);
                case 2 -> deposit(input);
                case 3 -> withdraw(input);
                case 4 -> showAccount(input);
                case 5 -> listAccounts();
                case 6 -> showStats();
                case 7 -> running = false;
                default -> System.out.println("Invalid option.");
            }
            System.out.println();
        }
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("""
            1) Create Account (Savings/Checking)
            2) Deposit
            3) Withdraw
            4) Show Account
            5) List All Accounts
            6) Stats (counts, total balance)
            7) Exit
            """);
    }

    private static void createAccount(Input in) {
        String owner = in.nextLine("Owner name: ");
        double initial = in.nextDouble("Initial deposit: ");
        String type = in.nextLine("Type [S= Savings, C= Checking]: ").trim().toUpperCase();

        Result<String> res;
        if ("S".equals(type)) {
            double rate = in.nextDouble("Interest rate (e.g., 1.5 for 1.5%): ");
            res = service.createAccount(new SavingsAccount(owner, initial, rate));
        } else if ("C".equals(type)) {
            double fee = in.nextDouble("Transaction fee (e.g., 0.50): ");
            res = service.createAccount(new CheckingAccount(owner, initial, fee));
        } else {
            System.out.println("Unknown type.");
            return;
        }
        System.out.println(res.message());
        res.value().ifPresent(id -> System.out.println("New Account ID: " + id));
    }

    private static void deposit(Input in) {
        String id = in.nextLine("Account ID: ");
        double amount = in.nextDouble("Amount to deposit: ");
        try {
            var res = service.deposit(id, amount);
            System.out.println(res.message());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid amount.");
        }
    }

    private static void withdraw(Input in) {
        String id = in.nextLine("Account ID: ");
        double amount = in.nextDouble("Amount to withdraw: ");
        try {
            var res = service.withdraw(id, amount);
            System.out.println(res.message());
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid amount.");
        }
    }

    private static void showAccount(Input in) {
        String id = in.nextLine("Account ID: ");
        try {
            var acc = service.getAccount(id);
            System.out.println(acc.details());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listAccounts() {
        var accounts = service.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet.");
            return;
        }
        Printer.printList("Accounts", accounts, a -> a.details());
    }

    private static void showStats() {
        System.out.println("Total accounts: " + service.count());
        System.out.printf("Total balance: %.2f%n", service.totalBalance());
        var types = service.countByType();
        System.out.println("By type: " + types);
    }
}
