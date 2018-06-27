package executionmanager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by root on 25.06.18.
 */
public class ExecutionManagerImpl implements ExecutionManager {

    public Context execute(Runnable callback, Runnable... tasks) {
        Collection<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }
        Thread thread = new Thread(new CallbackWrapper(callback, threads));
        thread.start();
        return new ContextImpl(threads);
    }

    private final class CallbackWrapper implements Runnable {
        private final Collection<Thread> threads;
        private final Runnable callback;
        public CallbackWrapper(Runnable callback, Collection<Thread> threads) {
            this.threads = threads;
            this.callback = callback;
        }
        @Override
        public void run() {
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            callback.run();
        }
    }
}
