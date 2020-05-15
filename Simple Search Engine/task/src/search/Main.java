package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.importData(args);

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

    void importData(String[] args) {
        String patch = "";
        for (int i = 0; i < args.length; i++) {
            if ("--data".equals(args[i])) {
                patch = args[i + 1];
            }
        }

        File file = new File(patch);
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                dataSet.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    void printMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    void findAPerson() {

        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String matchingStrategy = scanner.nextLine().toUpperCase();

        AtomicBoolean dataFound = new AtomicBoolean();
        StringBuilder foundData = new StringBuilder();
        dataFound.set(false);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String searchData = scanner.nextLine();

        switch (matchingStrategy) {
            case "ALL":
                findAll(dataFound, foundData, searchData);
                break;
            case "ANY":
                findAny(dataFound, foundData, searchData);
                break;
            case "NONE":
                findNone(dataFound, foundData, searchData);
                break;
            default:
                System.out.println("\nIncorrect option! Try again.");
        }

        if (dataFound.get()) {
            System.out.println(foundData);
        } else {
            System.out.println("No matching people found.");
        }
        System.out.println();
    }

    void findAll(AtomicBoolean dataFound, StringBuilder foundData, String searchData) {

        AtomicInteger count = new AtomicInteger();
        dataSet.forEach(value -> {
            boolean containsAll = true;
            for (String word : searchData.toLowerCase().split(" ")) {
                if (!value.toLowerCase().contains(word)) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll) {
                count.getAndIncrement();
                foundData.append("\n").append(value);
                dataFound.set(true);
            }
        });

        foundData.insert(0, " persons found:").insert(0, count.get());
    }

    void findAny(AtomicBoolean dataFound, StringBuilder foundData, String searchData) {

        AtomicInteger count = new AtomicInteger();
        dataSet.forEach(value -> {
            for (String word : searchData.toLowerCase().split(" ")) {
                if (value.toLowerCase().contains(word)) {
                    foundData.append("\n").append(value);
                    dataFound.set(true);
                    count.getAndIncrement();
                    break;
                }
            }
        });

        foundData.insert(0, " persons found:").insert(0, count.get());
    }

    void findNone(AtomicBoolean dataFound, StringBuilder foundData, String searchData) {

        AtomicInteger count = new AtomicInteger();
        dataSet.forEach(value -> {
            boolean contains = false;
            for (String word : searchData.toLowerCase().split(" ")) {
                if (value.toLowerCase().contains(word)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                foundData.append("\n").append(value);
                dataFound.set(true);
                count.getAndIncrement();
            }
        });

        foundData.insert(0, " persons found:").insert(0, count.get());
    }

    void printAllPeople() {
        System.out.println("\n=== List of people ===");
        dataSet.forEach(System.out::println);
        System.out.println();
    }

    void exit() {
        programWorked = false;
        System.out.println("\nBye!");
    }
}