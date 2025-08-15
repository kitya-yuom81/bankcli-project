import java.util.Scanner;


public class Main {
    private static final Bankservice service = new BankService();

    public static void main(String[] args) {
        System.out.println("=== Mini Banking CLI ===");
        var input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.nextInt("Choice (1-7): ");
            switch (choice) {
                case 1 -> createAccount(input);
                case 2 -> deposit(input);
                case 3 -> withdraw(input);
                case 4 -> showAccount(input);
                case 5 -> listAccounts();
                case 6 -> showStats();
                case 7 -> running = false;
                default -> System.out.println("Invalid choice");

            }
            System.out.println();
        }
        System.out.println("Bye");

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

}
