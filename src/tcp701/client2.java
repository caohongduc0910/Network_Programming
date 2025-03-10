/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp701;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author ADMINN
 */
public class client2 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 2206;
        String request = "B21DCCN234;701";

        try {
            Socket socket = new Socket(host, port);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(request.getBytes());
            out.flush();

            byte[] receiver = new byte[1024];
            int numberOfByte = in.read(receiver);
            String s = new String(receiver, 0, numberOfByte);

            String subString[] = s.split(", ");

            ArrayList<Integer> arr = new ArrayList<>();
            for (String subString1 : subString) {
                arr.add(Integer.parseInt(subString1));
            }

            Collections.sort(arr, Collections.reverseOrder());

            int miN = 100000;

            for (int i = 0; i < arr.size() - 1; i++) {
                miN = Math.min(miN, arr.get(i) - arr.get(i + 1));
            }

            String result = "" + miN + ", ";

            for (int i = 0; i < arr.size() - 1; i++) {
                if (arr.get(i) - arr.get(i + 1) == miN) {
                    result += arr.get(i) + ", " + arr.get(i + 1);
                    break;
                }
            }
            
            System.out.println(result);
            
            out.write(result.getBytes());
            out.flush();

        } catch (Exception e) {
        }
    }
}
