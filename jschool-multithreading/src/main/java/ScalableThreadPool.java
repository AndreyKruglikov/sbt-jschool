import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool implements ThreadPool {

    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Thread> innerQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;
    private final int minThreads;
    private final int maxThreads;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < minThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
        Thread additional = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    if (!workQueue.isEmpty()) {
                        if (innerQueue.size() < maxThreads - minThreads) {
                            Thread thread = new Thread(workQueue.poll());
                            innerQueue.add(thread);
                            thread.start();
                        } else {
                            for (Thread thread : innerQueue) {
                                if (!thread.isAlive()) {
                                    innerQueue.remove(thread);
                                }
                            }
                        }
                    }
                }
            }
        });
        additional.start();
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
