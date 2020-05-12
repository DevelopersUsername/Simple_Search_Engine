package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
    List<String> dataSet;
    Map<String, List<Integer>> keyMap;
    boolean programWorked;

    public SearchEngine() {
        this.scanner = new Scanner(System.in);
        this.dataSet = new ArrayList<>();
        this.keyMap = new HashMap<>();
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
        initKeyMap();
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

        if (keyMap.containsKey(searchData.toLowerCase())) {
            List<Integer> keyList = keyMap.get(searchData.toLowerCase());
            foundData.append("\n").append(keyList.size()).append(" persons found:");
            keyList.forEach(key -> foundData.append("\n").append(dataSet.get(key)));

            System.out.println(foundData);
        } else
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

    void initKeyMap() {

        dataSet.forEach(value -> {
            String[] arrayKey = value.toLowerCase().split("\\s");

            for (String s : arrayKey) {
                List<Integer> currentList;
                if (keyMap.containsKey(s)) {
                    currentList = keyMap.get(s);
                } else {
                    currentList = new ArrayList<>();
                }
                currentList.add(dataSet.indexOf(value));

                keyMap.put(s, currentList);
            }
        });
    }
}