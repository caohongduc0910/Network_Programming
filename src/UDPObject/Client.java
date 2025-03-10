/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPObject;

import TCP.Student;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ADMINN
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN234;l6fzF7yW";
        InetAddress host = InetAddress.getByName("203.162.10.109");
        int port = 2209;

        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sending = request.getBytes();
            DatagramPacket sendingRequest = new DatagramPacket(sending, sending.length, host, port);
            socket.send(sendingRequest);

            byte[] receiving = new byte[1024];
            DatagramPacket receivingString = new DatagramPacket(receiving, receiving.length, host, port);
            socket.receive(receivingString);

            String requestId = new String(receivingString.getData(), 0, 8);
            System.out.println(requestId);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(receivingString.getData(), 8, receivingString.getLength() - 8);
            ObjectInputStream os = new ObjectInputStream(bis);
            Student student = (Student) os.readObject();
            System.out.println(student);
        } catch (Exception e) {
        }   

    }
}
