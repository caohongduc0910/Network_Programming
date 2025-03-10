/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPCharacterStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMINN
 */
public class client {

    public static void main(String[] args) {
        String request = "B21DCCN234;X8BfmwdN";
        int port = 2208;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.write(request);
            out.newLine();
            out.flush();
            
            String s = in.readLine();
            System.out.println(s);
            
            String answer = "";
            
            Map<Character, Integer> m = new HashMap<>();
            
            for(int i = 0; i < s.length(); i++){
                if(Character.isLetter(s.charAt(i)) && !m.containsKey(s.charAt(i))) {
                    m.put(s.charAt(i), 1);
                }
            }
            
            for(int i = 0; i < s.length(); i++){
                if(m.containsKey(s.charAt(i)) && m.get(s.charAt(i)) != 0) {
                    answer += s.charAt(i);
                    m.put(s.charAt(i), 0);
                }
            }
            
            out.write(answer);
            out.newLine();
            out.flush();
            out.close();
            in.close();
            socket.close();
            
        } catch (Exception e) {
        }
    }
}
