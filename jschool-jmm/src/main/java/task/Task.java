package task;

import java.util.concurrent.Callable;

public class Task<T> {

    private Callable<? extends T> callable;
    private volatile T result;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (result == null) {
            synchronized (this) {
                try {
                    result = callable.call();
                    return result;
                } catch (Exception e) {
                    throw new SimpleException(e);
                }
            }
        } else {
            return result;
        }
    }
}
