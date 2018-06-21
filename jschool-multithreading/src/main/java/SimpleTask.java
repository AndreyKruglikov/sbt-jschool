import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleTask implements Runnable {
    private final String name;
    public SimpleTask(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(this + " started");
        int sum = 0;
        for (int i = 0; i < new Random().nextInt(10000000); i++) {
            sum = i + sum;
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The sum of " + this + ": " + sum);
    }
    @Override
    public String toString() {
        return "SimpleTask{" +
                "name='" + name + '\'' +
                '}';
    }
}
