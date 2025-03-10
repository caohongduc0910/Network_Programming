package TCPDataStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class client {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        String request = "B21DCCN234;FmqQc3bK";
        int port = 2207;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(request);
            dos.flush();

            int a = dis.readInt();
            int b = dis.readInt();

//            System.out.println(a);
//            System.out.println(b);
            
            dos.writeInt(gcd(a, b));
            dos.flush();
            dos.writeInt(lcm(a, b));
            dos.flush();
            dos.writeInt(a + b);
            dos.flush();
            dos.writeInt(a * b);
            dos.flush();
            
            dos.close();
            dis.close();
            socket.close();
        } catch (Exception e) {
        }
    }
}
