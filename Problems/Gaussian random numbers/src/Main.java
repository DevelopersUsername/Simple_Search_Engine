import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random;
        boolean done;

        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        while (true) {
            random = new Random(k);
            done = true;
            for (int i = 0; i < n; i++) {
                if (random.nextGaussian() > m) {
                    done = false;
                    break;
                }
            }

            if (done) {
                System.out.println(k);
                break;
            } else
                k++;
        }
    }
}