package executionmanager;

/**
 * Created by root on 25.06.18.
 */
public interface Context {
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
}
