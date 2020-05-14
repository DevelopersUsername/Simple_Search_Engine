import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> keyMap = new HashMap<>();

        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int key = scanner.nextInt();
            keyMap.put(key, keyMap.getOrDefault(key, 0) + 1);
        }
        System.out.println(keyMap.getOrDefault(scanner.nextInt(), 0));
    }
}