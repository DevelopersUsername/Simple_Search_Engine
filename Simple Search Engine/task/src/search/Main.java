package search;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.enterData();

        while (searchEngine.programWorked) {
            searchEngine.printMenu();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    searchEngine.findAPerson();
                    break;
                case 2:
                    searchEngine.printAllPeople();
                    break;
                case 0:
                    searchEngine.exit();
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }
}

class SearchEngine {

    Scanner scanner;
    Set<String> dataSet;
    boolean programWorked;

    public SearchEngine() {
        this.scanner = new Scanner(System.in);
        this.dataSet = new LinkedHashSet<>();
        this.programWorked = true;
    }

    void enterData() {
        System.out.println("Enter the number of people:");
        int countLines = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter all people:");
        for (int i = 0; i < countLines; i++) {
            dataSet.add(scanner.nextLine());
        }
    }

    void printMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    void findAPerson() {

        AtomicBoolean dataFound = new AtomicBoolean();
        StringBuilder foundData = new StringBuilder();
        dataFound.set(false);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String searchData = scanner.nextLine();

        dataSet.forEach(value -> {
            if (value.toLowerCase().contains(searchData.trim().toLowerCase())) {
                foundData.append("\n").append(value);
                dataFound.set(true);
            }
        });

        if (dataFound.get())
            System.out.println(foundData);
        else
            System.out.println("No matching people found.");
    }

    void printAllPeople() {
        System.out.println("\n=== List of people ===");
        dataSet.forEach(System.out::println);
    }

    void exit() {
        programWorked = false;
        System.out.println("\nBye!");
    }
}