import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutionManagerImpl implements ExecutionManager{
    private static final int MAX_THREADS = 10;
    private Runnable[] tasks;
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREADS);

        return null;
    }
}
