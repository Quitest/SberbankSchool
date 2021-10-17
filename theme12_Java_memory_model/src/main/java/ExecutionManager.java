public interface ExecutionManager {
    /**
     * Принимает массив тасков, это задания которые ExecutionManager должен выполнять параллельно.
     * После завершения всех тасков должен выполниться callback (ровно 1 раз).
     * @param callback
     * @param tasks
     * @return
     */
    Context execute(Runnable callback, Runnable... tasks);
}
