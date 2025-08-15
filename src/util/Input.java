package util;



import java.io.InputStream;
import java.util.Scanner;

public class Input {
    private final Scanner sc;

    public Input(InputStream in) {
        this.sc = new Scanner(in);
        //this refer parameter to obj's field
    }

    public String nextLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
        // remove space in the start and end  of string
    }

    public int nextInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public double nextDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
                //We use Integer.parseInt(...) here because Scanner.nextLine() always gives you a String, even if the user typed numbers.
            }
        }
    }
}
