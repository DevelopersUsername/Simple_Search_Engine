package search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> firstLine = new HashMap<>();

        int index = 1;
        for (String elem : scanner.nextLine().split("\\s")) {
            firstLine.put(elem, Integer.toString(index));
            index++;
        }

        System.out.println(firstLine.getOrDefault(scanner.nextLine(), "Not Found"));
    }
}
