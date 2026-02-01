import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.Duration;
import java.time.Instant;

public class ping {

    public static void main(String[] args) throws Exception {
        InetAddress ServerAddress = null;
        try {
            ServerAddress = InetAddress.getByName(args[0]);

        }catch (UnknownHostException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }

        int serverPortNumber = Integer.valueOf(args[1]);

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(1000); //
        int sequenceNumber = 0;
        
        System.out.println("Ping for " + ServerAddress.getHostAddress());
        while (sequenceNumber < 10){

            Instant start = Instant.now();

            byte[] data = new byte[1024];

            DatagramPacket ping = new DatagramPacket(data , data.length , ServerAddress , serverPortNumber);
            socket.send(ping);

            DatagramPacket response = new DatagramPacket(new byte[1024] , 1024);
            try {
                socket.receive(response);
            }catch (SocketTimeoutException e){
                System.out.println("PING "+ sequenceNumber + " LOST");
                e.getMessage();
                sequenceNumber++;
                continue;
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            System.out.println(
                "Reply from " +
                        response.getAddress().getHostAddress() +
                        ": "+" Bytes= "+data.length + " Delay: "+ Duration.between(start, Instant.now()).toMillis()  + "ms");
            sequenceNumber++;

        }

    }


}
