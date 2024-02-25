import java.io.*;
import java.net.*;

public class Client implements Runnable {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    Client(String serverAddress, int serverPort, String name) throws IOException {
        System.out.println("Client Started");
        socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to server: " + serverAddress);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.name = name;

        out.println(this.name);
    }

    public void run(){
        while(!socket.isClosed()){
            try{
                String msg = in.readLine();
                System.out.println(msg);

            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String serverAddress;
        int serverPort = 5000;
        
        System.out.print("Enter Server Address to connect to: ");
        serverAddress = br.readLine();
        System.out.print("Enter Username: ");
        String name = br.readLine();

        Client clientObj = new Client(serverAddress, serverPort , name);
        Thread thread = new Thread(clientObj);
        thread.start();

        System.out.println("You can now Start Chatting :)");
        System.out.println("<<<------------------------->>>");
        System.out.println("");
        
        while(true){
            String msg = br.readLine();
            clientObj.out.println(name+": "+msg);
        }
    }
}