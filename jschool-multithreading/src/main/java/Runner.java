public class Runner {
    public static void main(String[] args) {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new SimpleTask("SimpleTask" + Integer.toString(i)));
        }
    }
}
