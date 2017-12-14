package agentImpl;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Fred on 2017-12-13.
 */
public class Agent extends Thread {

    public static void main(String[] args) {

        Agent a = new Agent(1000, 1000);
        a.start();
    }

    private final int PORT_NUMBER = 8080;
    private final long SYN_TIME;
    private Counter counter;
    private Communicator communicator;

    public Agent(long initCounter, long synTime) {
        counter = new Counter(initCounter);
        SYN_TIME = synTime;
        try {
            communicator = new Communicator(PORT_NUMBER, SYN_TIME);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        super.start();
        counter.start();
        communicator.start();
    }
}
