import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    private static final int THREADS_MAX = 15;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String fileWithNumbers = "numbers.txt";

        generateFile(fileWithNumbers);

        //TODO переписать на ThreadPool.
        for (int i = 0; i < THREADS_MAX; i++) {
            Thread thread = new Thread(new FactorialTask(fileWithNumbers));
            thread.start();
            thread.join();
        }
    }

    public static void generateFile(String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int n = random.nextInt(51);
                writer.println(n);
            }
        }
    }
}
