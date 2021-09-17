import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialTask implements Runnable {
    private final String file;

    public FactorialTask(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        int number = takeANumberFromFile();
        //Если нет чисел в файле, то просто прекращаем выполнение.
        if (number==-1){
            return;
        }

        int multiplier = number;
        BigDecimal factorial = BigDecimal.ONE;

        while (multiplier > 0) {
            BigDecimal n = new BigDecimal(multiplier--);
            factorial = factorial.multiply(n);
        }
        System.out.printf("Thread %s has calculated %d! = %s %n", Thread.currentThread().getName(), number, factorial);
    }

    private int takeANumberFromFile() {
        int n = 0;

        synchronized (file) {
            try{
                List<String> numbersList = Files.lines(Path.of(file))
                        .filter(str -> !str.isEmpty())
                        .collect(Collectors.toList());
                //TODO переработать обработку исключительной ситуации, когда кончились числа в файле.
               //Если список пустой, то возвращаем -1, т.к. факториал отрицательного числа не считается, вроде бы.
                if (numbersList.isEmpty()){
                    return -1;
                }
                n = Integer.parseInt(numbersList.get(numbersList.size() - 1));
                PrintWriter writer = new PrintWriter(file);
                for (int i = 0; i < numbersList.size() - 1; i++) {
                    writer.println(numbersList.get(i));
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return n;
    }
}
