package executionmanager;

import java.util.Collection;

/**
 * Created by root on 25.06.18.
 */
public class ContextImpl implements Context {

    private Collection<Thread> threads;

    public ContextImpl(Collection<Thread> threads) {
        this.threads = threads;
    }

    @Override
    public int getCompletedTaskCount() {
        int completed = 0;
        synchronized (threads) {
            for (Thread thread : threads) {
                if (!thread.isInterrupted() && thread.getUncaughtExceptionHandler() == null)
                    ++completed;
            }
        }
        return completed;
    }

    @Override
    public int getFailedTaskCount() {
        // TODO
        return threads.size() - (getCompletedTaskCount() + getInterruptedTaskCount());
    }

    @Override
    public int getInterruptedTaskCount() {
        int interrupted = 0;
        synchronized (threads) {
            for (Thread thread : threads) {
                if (thread.isInterrupted())
                    ++interrupted;
            }
        }
        return interrupted;
    }

    @Override
    public void interrupt() {
        // TODO
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public boolean isFinished() {
        // TODO
        if (getCompletedTaskCount() + getInterruptedTaskCount() == threads.size())
            return true;
        return false;
    }
}
