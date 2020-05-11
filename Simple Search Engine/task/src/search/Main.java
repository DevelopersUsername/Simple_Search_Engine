package search;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.enterData();
        searchEngine.searchData();
    }
}

class SearchEngine {

    Scanner scanner;
    Set<String> dataSet;

    public SearchEngine() {
        this.scanner = new Scanner(System.in);
        this.dataSet = new LinkedHashSet<>();
    }

    void enterData() {
        System.out.println("Enter the number of people:");
        int countLines = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter all people:");
        for (int i = 0; i < countLines; i++) {
            dataSet.add(scanner.nextLine());
        }
    }

    void searchData() {
        System.out.println("\nEnter the number of search queries:");
        int countSearch = Integer.parseInt(scanner.nextLine());
        AtomicBoolean dataFound = new AtomicBoolean();

        for (int i = 0; i < countSearch; i++) {
            System.out.println("\nEnter data to search people:");
            String searchData = scanner.nextLine();
            StringBuilder foundData = new StringBuilder("\nFound people:");
            dataFound.set(false);

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
    }
}