/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPDataType;

import static java.lang.Math.sqrt;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author ADMINN
 */
public class client {

    public static boolean prime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN234;P2zKIynO";
        int port = 2207;
        InetAddress host = InetAddress.getByName("203.162.10.109");

        try {
            DatagramSocket socket = new DatagramSocket();
            byte sending[] = request.getBytes();
            DatagramPacket sendingPacket = new DatagramPacket(sending, sending.length, host, port);
            socket.send(sendingPacket);

            byte receive[] = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receive, receive.length, host, port);
            socket.receive(receivePacket);

            String s = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String temp[] = s.split(";");
            String answer = temp[0] + ";";
            int number = Integer.parseInt(temp[1]);
            int cnt = 0;
            int start = 2;
            while (cnt < number) {
                if (prime(start)) {
                    cnt++;
                    if (cnt != number) {
                        answer += start + ",";
                    } else {
                        answer += start;
                    }
                }
                start++;
            }
            
            byte sending1[] = answer.getBytes();
            DatagramPacket sendingPacket1 = new DatagramPacket(sending1, sending1.length, host, port);
            socket.send(sendingPacket1);
            
            socket.close();
            System.out.println(answer);
        } catch (Exception e) {
        }
    }
}
