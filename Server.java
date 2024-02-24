import java.net.*;
import java.io.*;

public class Server implements Runnable {
    ServerSocket serverSocket;
    PrintWriter speaker;

    Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server Started on port: "+port);
        System.out.println("Waiting for the Clients...");
    }

    @Override
    public void run(){
        while(!serverSocket.isClosed()){
            try{
                Socket socket = serverSocket.accept();
                BufferedReader listen = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = listen.readLine();
                Member.broadcast(name+" has Entered the Chat!!!", name);

                Member member = new Member(socket, name);
                Member.group.add(member);

                Thread thread = new Thread(member);
                thread.start();

            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        int port = 5000;
        Server myServer = new Server(port);
        Thread thread = new Thread(myServer);
        thread.start();
    }
}
