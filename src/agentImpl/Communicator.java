package agentImpl;

import java.io.IOException;
import java.net.*;

/**
 * Created by Fred on 2017-12-14.
 */
public class Communicator extends  Thread {

    private final long SYN_TIME;
    private final DatagramSocket SOCKET;
    private DatagramPacket packet;

    public Communicator(int port, long synTime) throws SocketException {

        SYN_TIME = synTime;
        SOCKET = new DatagramSocket(port);
//        packet = new DatagramPacket(buf, buf.length);

    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] buf = new byte[256];

                String dString = "ACK";

                buf = dString.getBytes();

                InetAddress group = InetAddress.getByName("203.0.113.0");

                packet = new DatagramPacket(buf, buf.length, group, 9999);

                SOCKET.send(packet);


                try {
                    Thread.sleep(SYN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                MulticastSocket mSocket = new MulticastSocket(9999);

                mSocket.joinGroup(group);

                packet = new DatagramPacket(buf, buf.length);
                mSocket.receive(packet);

                String received = new String(packet.getData());
                System.out.println("Quote of the Moment: " + received);

                mSocket.leaveGroup(group);


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }




}
