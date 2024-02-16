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
        // Obtain references to the packet's array of bytes.
        byte[] buf = request.getData();

        // Wrap the bytes in a byte array input stream,
        // so that you can read the data as a stream of bytes.
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);

        // Wrap the byte array output stream in an input stream reader,
        // so you can read the data as a stream of characters.
        InputStreamReader isr = new InputStreamReader(bais);

        // Wrap the input stream reader in a bufferred reader,
        // so you can read the character data a line at a time.
        // (A line is a sequence of chars terminated by any combination of \r and \n.)
        BufferedReader br = new BufferedReader(isr);

        // The message data is contained in a single line, so read this line.
        String line = br.readLine();

        // Print host address and data received from it.
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress()
                         );
    }

}
