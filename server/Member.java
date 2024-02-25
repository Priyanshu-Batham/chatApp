import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Member implements Runnable {
    static ArrayList<Member> group = new ArrayList<>();

    Socket socket;
    String name;
    BufferedReader in;
    PrintWriter out;


    public Member(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        while(!socket.isClosed()){
            try{
                String msg = in.readLine();
                broadcast(msg, name);

            }catch(IOException e){
                System.out.println(e);
            }
        }
        //if client is disconnected and the socket is closed then:
        removeMember();

    }
    void removeMember(){
        if(group.contains(this)){
            group.remove(this);
        }
    }

    // Not using it right now
    public static void broadcast(String msg, String notToBroadcast){
        for(Member mem: group){
            if(!mem.name.equals(notToBroadcast)){
                mem.out.println(msg);
            }
        }
    }
}
