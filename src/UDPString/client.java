/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPString;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMINN
 */
public class client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN234;v5yHXRFp";
        int port = 2208;
        InetAddress host = InetAddress.getByName("203.162.10.109");

        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sending = request.getBytes();
            DatagramPacket sendingRequest = new DatagramPacket(sending, sending.length, host, port);
            socket.send(sendingRequest);

            byte[] receiving = new byte[1024];
            DatagramPacket receivingString = new DatagramPacket(receiving, receiving.length, host, port);
            socket.receive(receivingString);
            String s = new String(receivingString.getData(), 0, receivingString.getLength());

            System.out.println(s);

            String[] temp = s.split(";");
            String answer = temp[0] + ";";
            String newS = temp[1];
//            int cnt = 1;
//            for (int i = 1; i < newS.length(); i++) {
//                if (newS.charAt(i) == newS.charAt(i - 1)) {
//                    cnt++;
//                } else {
//                    answer += Integer.toString(cnt) + newS.charAt(i - 1);
//                    cnt = 1;
//                }
//
//                if (i == newS.length() - 1) {
//                    answer += Integer.toString(cnt) + newS.charAt(i);
//                }
//
//                System.out.println(answer);
//            }
            Map<Character, Integer> m = new HashMap<>();
            for (int i = 0; i < newS.length(); i++) {
                if (m.containsKey(newS.charAt(i))) {
                    int cnt = m.get(newS.charAt(i)) + 1;
                    m.put(newS.charAt(i), cnt);
                } else {
                    m.put(newS.charAt(i), 1);
                }
            }

            for (int i = 0; i < newS.length(); i++) {
                if (m.containsKey(newS.charAt(i)) && m.get(newS.charAt(i)) > 0) {
                    answer += Integer.toString(m.get(newS.charAt(i))) + newS.charAt(i);
                    m.put(newS.charAt(i), 0);
                }
            }
            
            System.out.println(answer);

            byte[] sending2 = answer.getBytes();
            DatagramPacket sendingAnswer = new DatagramPacket(sending2, sending2.length, host, port);
            socket.send(sendingAnswer);

            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
