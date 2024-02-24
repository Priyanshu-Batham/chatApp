import java.net.*;
import java.io.*;

public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader listen;
    PrintWriter speak;

    Server(int port)throws IOException{
        server = new ServerSocket(port);
        System.out.println("Server Started on port: "+port);
        System.out.println("Waiting for a Client...");
        socket = server.accept();
        System.out.println("Accepted Client");
        listen = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        speak = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void main(String args[]) throws IOException {
        int port = 5000;
        Server myServer = new Server(port);

        while(true){
            String msg = myServer.listen.readLine();
            System.out.println("CLIENT: "+msg);

            String response = "OKAY";
            System.out.println("SERVER: "+response);

            myServer.speak.println(response);
        }
    }
}
