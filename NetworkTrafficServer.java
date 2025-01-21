import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class NetworkTrafficServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter number of packets:");
            int packetCount = Integer.parseInt(consoleInput.readLine());

            System.out.println("Enter packet size (bytes):");
            int packetSize = Integer.parseInt(consoleInput.readLine());

            System.out.println("Enter interval between packets (ms):");
            int interval = Integer.parseInt(consoleInput.readLine());

            byte[] packet = new byte[packetSize];
            for (int i = 0; i < packetSize; i++) {
                packet[i] = (byte) i;
            }

            System.out.println("Starting to send packets...");

            for (int i = 0; i < packetCount; i++) {
                output.write(packet);
                output.flush();
                System.out.println("Sent packet " + (i + 1));
                Thread.sleep(interval);
            }

            System.out.println("Finished sending packets.");
            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}