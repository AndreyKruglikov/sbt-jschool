import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by root on 21.06.18.
 */
public class FixedThreadPool implements ThreadPool {

    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;
    private final int nThreads;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
    }

    @Override
    public void start() {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
