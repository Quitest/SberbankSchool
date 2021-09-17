import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    private static final int THREADS_MAX = 10;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String fileWithNumbers = "numbers.txt";
//        System.out.println("START" + Thread.currentThread().getName());

        generateFile(fileWithNumbers);

        for (int i = 0; i < THREADS_MAX; i++){
            Thread thread = new Thread(new FactorialTask(fileWithNumbers));
            thread.start();
            thread.join();
        }

//            Thread t0 = new Thread(new FactorialTask(fileWithNumbers));
//        Thread t1 = new Thread(new FactorialTask(fileWithNumbers));
//
//        t0.start();
//        t1.start();



//        System.out.println("END"+ Thread.currentThread().getName());
    }

    public static void generateFile(String fileName) throws FileNotFoundException {
        PrintWriter fos = new PrintWriter(fileName);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt(51);
            fos.println(n);
        }
        fos.close();
    }
}
