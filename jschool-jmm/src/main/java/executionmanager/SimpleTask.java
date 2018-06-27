package executionmanager;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 25.06.18.
 */
public class SimpleTask implements Runnable {
    private String name;
    private Random random = new Random();
    public SimpleTask(String name) {
        this.name = name;
    }
    @Override
    public void run() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            System.out.println(this + " interrupted");
//            return;
//        }
        if (!Thread.interrupted()) {
            if (random.nextInt() < 0)
                try {
                    throw new Exception();
                } catch (Exception exc) {
                    System.out.println(this + " terminated with exception");
                    Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println(e);
                        }
                    });
                    return;
                }
            System.out.println(this + " completed");
        }
    }
    @Override
    public String toString() {
        return "SimpleTask{" +
                "name='" + name + '\'' +
                '}';
    }
}
