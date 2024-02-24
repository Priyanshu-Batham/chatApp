import java.io.*;
import java.net.*;

public class Client{
    Socket socket;
    PrintWriter speaker;

    Client(String serverAddress, int serverPort) throws IOException {
        System.out.println("Client Started");
        socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to server: " + serverAddress);
        speaker = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void main(String[] args) throws IOException{
        String serverAddress = "localhost";
        int serverPort = 5000;
        Client clientObj = new Client(serverAddress, serverPort);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.print("Enter Message: ");
            String msg = br.readLine();
            clientObj.speaker.println(msg);
        }
    }
}