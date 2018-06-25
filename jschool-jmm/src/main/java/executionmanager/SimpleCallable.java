package executionmanager;

/**
 * Created by root on 25.06.18.
 */
public class SimpleCallable implements Runnable {
    @Override
    public void run() {
        System.out.println("SimpleCallable did callback");
    }
}
