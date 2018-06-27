package task;

import java.util.Random;
import java.util.concurrent.Callable;

public class SimpleCallable implements Callable<String> {
    private String name;
    private Random random = new Random();
    public SimpleCallable(String name) {
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        int rnd = random.nextInt();
        System.out.println(this + ": rnd = " + rnd);
        if (rnd < 0)
            throw new Exception();
        return this + "success";
    }
    @Override
    public String toString() {
        return "SimpleCallable{" +
                "name='" + name + '\'' +
                '}';
    }
}
