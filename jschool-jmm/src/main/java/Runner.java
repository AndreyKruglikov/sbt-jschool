import executionmanager.*;
import task.SimpleException;
import task.Task;

/**
 * Created by root on 25.06.18.
 */
public class Runner {

    public static void main(String... args) {
        // Task 1
//        final Task<String> stringTask = new Task<>(new task.SimpleCallable("simple name"));
//        for(int i=0; i< 10; i++) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(stringTask.get());
//                    } catch (SimpleException e) {
//                        System.out.println(e + " in " + Thread.currentThread().getName());
//                    }
//                }
//            });
//            thread.start();
//        }

        // Task 2
        Runnable[] runnables = new Runnable[10];
        for (int i = 0; i < runnables.length; i++) {
            runnables[i] = new SimpleTask(String.valueOf(i));
        }
        ExecutionManager executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(new SimpleCallable(), runnables);
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        context.interrupt();
        System.out.println("Completed tasks: " + context.getCompletedTaskCount());
        System.out.println("Interrupted tasks: " + context.getInterruptedTaskCount());
        System.out.println("Failed tasks: " + context.getFailedTaskCount());
    }
}
