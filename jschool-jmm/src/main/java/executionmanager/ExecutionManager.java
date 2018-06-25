package executionmanager;

/**
 * Created by root on 25.06.18.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
