package agentImpl;

/**
 * Created by Fred on 2017-12-13.
 */
public class Counter extends Thread {

    private volatile long millis;

    public Counter(long initCounter) {
        if(initCounter >= 0) { millis = initCounter; }
    }

    public synchronized long getMillis() {
        return millis;
    }

    public synchronized void setMillis(long value) {
        millis = value;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            millis++;
            System.out.println(millis);
        }
    }
}
