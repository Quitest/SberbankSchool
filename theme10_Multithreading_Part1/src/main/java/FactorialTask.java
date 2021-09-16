import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialTask implements Runnable {
    private String file;

    public FactorialTask(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        int number = takeANumberFromFile();
        long factorial = 1;
        while (number > 0) {
            factorial *= number--;
        }
        System.out.printf("Thread %s has calculated %d! = %d %n", Thread.currentThread().getName(), number, factorial);
    }

    private int takeANumberFromFile() {
        int n = 0;

        try {
            List<String> numbersList = Files.lines(Path.of(file))
                    .collect(Collectors.toList());
            n = Integer.parseInt(numbersList.get(numbersList.size() - 2));
            PrintWriter writer = new PrintWriter(file);
            for (int i = 0; i < numbersList.size() - 1; i++) {
                writer.println(numbersList.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
