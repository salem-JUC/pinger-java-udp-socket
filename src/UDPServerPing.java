import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
public class UDPServerPing {

    public static void main(String[] args) throws Exception {
        int portNumber = Integer.parseInt(args[0]);
        if (portNumber < 0 && portNumber > 65535){//65535 is highest  TCP port number
            throw new Exception("Invalid Port Number");
        }else {
        portNumber = Integer.parseInt(args[0]);

        DatagramSocket socket = new DatagramSocket(711);

        while (true) {
            DatagramPacket ping = new DatagramPacket(new byte[1024], 1024);

            socket.receive(ping);

            printData(ping);

            byte[] buf = ping.getData();
            InetAddress address = ping.getAddress();
            int pingerPortNumber = ping.getPort();
            DatagramPacket response = new DatagramPacket(buf, buf.length, address, pingerPortNumber);

            socket.send(response);
        }
        }
    }

    private static void printData(DatagramPacket request) throws Exception
    {
        byte[] buf = request.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        InputStreamReader isr = new InputStreamReader(bais);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress()
                         );
    }

}
