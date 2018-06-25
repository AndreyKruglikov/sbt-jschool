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
        for (Thread thread : threads) {
            if (!thread.isAlive() && !thread.isInterrupted())
                ++completed;
        }
        return completed;
    }

    @Override
    public int getFailedTaskCount() {
        return threads.size() - (getCompletedTaskCount() + getInterruptedTaskCount());
    }

    @Override
    public int getInterruptedTaskCount() {
        int interrupted = 0;
        for (Thread thread : threads) {
            if (thread.isInterrupted())
                ++interrupted;
        }
        return interrupted;
    }

    @Override
    public void interrupt() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public boolean isFinished() {
        if (getCompletedTaskCount() + getInterruptedTaskCount() == threads.size())
            return true;
        return false;
    }
}
