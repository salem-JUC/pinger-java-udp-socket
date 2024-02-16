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


            printData(response , Duration.between(start , Instant.now()).toMillis());

            sequenceNumber++;

        }

    }

    private static void printData(DatagramPacket request , long delay) throws Exception
    {
        byte[] data = request.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        InputStreamReader isr = new InputStreamReader(bais);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress() +
                        ": "+"Bytes="+data.length + "Delay: "+ delay );
    }
}
