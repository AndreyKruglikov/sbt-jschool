import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    private final int count;
    private final ConcurrentLinkedQueue<Thread> workingQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Runnable> innerQueue = new ConcurrentLinkedQueue<>();

    public FixedThreadPool(int count) {
        this.count = count;
    }

    public void start() {
        Thread starter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!innerQueue.isEmpty()) {
                        if (workingQueue.size() < count) {
                            Thread thread = new Thread(innerQueue.poll());
                            workingQueue.add(thread);
                            thread.start();
                        } else {
                            for (Thread thread : workingQueue) {
                                if (!thread.isAlive()) {
                                    workingQueue.remove(thread);
                                }
                            }
                        }
                    }
                }
            }
        });
        starter.start();
    }

    public void execute(Runnable runnable) {
        innerQueue.add(runnable);
    }
}
