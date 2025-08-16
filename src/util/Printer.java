package util;

import java.util.List;
import java.util.function.Function;

public class Printer {
    public static <T> void printList(String title, List<T> items, Function<T, String> toLine) {
        System.out.println("=== " + title + " (" + items.size() + ") ===");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + toLine.apply(items.get(i)));
        }
    }
}
