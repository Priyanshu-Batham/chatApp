import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Member implements Runnable {
    static ArrayList<Member> group = new ArrayList<>();
    static String path = "../backup/backup.txt";
    static FileWriter file;

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

    public static void broadcast(String msg, String notToBroadcast){
        try{
            file = new FileWriter(path, true);
            file.append(msg + "\n");
            file.close();
        }catch(IOException e){
            System.out.println(e);
        }
        for(Member mem: group){
            if(!mem.name.equals(notToBroadcast)){
                mem.out.println(msg);
            }
        }
    }
}
