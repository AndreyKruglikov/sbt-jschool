import task.SimpleCallable;
import task.SimpleException;
import task.Task;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static void main(String[] args) {
        // Task 1
        final Task<String> stringTask = new Task<>(new SimpleCallable("simple name"));
        for(int i=0; i< 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(stringTask.get());
                    } catch (SimpleException e) {
                        System.out.println(e + " in " + Thread.currentThread().getName());
                    }
                }
            });
            thread.start();
        }
    }
}
