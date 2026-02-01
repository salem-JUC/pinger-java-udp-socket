import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
public class UDPServerPing {

    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);

        portNumber = Integer.parseInt(args[0]);
        DatagramSocket socket = null;
        try{
            socket = new DatagramSocket(portNumber);
        }catch(SocketException sException){
            System.err.println(sException.getMessage());
            System.exit(1);
        }

        while (true) {
            DatagramPacket ping = new DatagramPacket(new byte[2000], 2000);

            socket.receive(ping);

            System.out.println(
                "Received from " +ping.getAddress().getHostAddress() + " Byte = " + ping.getData().length);

            byte[] buf = ping.getData();
            InetAddress address = ping.getAddress();
            int pingerPortNumber = ping.getPort();
            DatagramPacket response = new DatagramPacket(buf, buf.length, address, pingerPortNumber);

            socket.send(response);
        }
        
    }


}
