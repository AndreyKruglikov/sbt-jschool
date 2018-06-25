package executionmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * Created by root on 25.06.18.
 */
public class ExecutionManagerImpl implements ExecutionManager {

    public Context execute(Runnable callback, Runnable... tasks) {
        Collection<Thread> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(tasks.length);
        for (Runnable task : tasks) {
            Thread thread = new Thread(new TaskWrapper(task, latch));
            threads.add(thread);
            thread.start();
        }
        try {
            latch.await();
            callback.run();
            return new ContextImpl(threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final class TaskWrapper implements Runnable {
        private final Runnable runnable;
        private final CountDownLatch latch;
        public TaskWrapper(Runnable runnable, CountDownLatch latch) {
            this.runnable = runnable;
            this.latch = latch;
        }
        @Override
        public void run() {
            runnable.run();
            latch.countDown();
        }
    }
}
