import java.io.*;
import java.net.*;
import java.util.*;

public class NetworkTrafficClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server: " + serverAddress);

            DataInputStream input = new DataInputStream(socket.getInputStream());

            long totalBytesReceived = 0;
            int packetCount = 0;
            long startTime = System.currentTimeMillis();

            byte[] buffer = new byte[1024]; // Adjust size based on expected packet size
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                totalBytesReceived += bytesRead;
                packetCount++;
                System.out.println("Received packet " + packetCount + " (" + bytesRead + " bytes)");
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Connection closed.");
            System.out.println("Total packets received: " + packetCount);
            System.out.println("Total bytes received: " + totalBytesReceived);
            System.out.println("Elapsed time: " + elapsedTime + " ms");
            System.out.println("Average speed: " + (totalBytesReceived / (elapsedTime / 1000.0)) + " bytes/second");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
