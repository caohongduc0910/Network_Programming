/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPCharacterStream3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
//[Mã câu hỏi (qCode): goaZSnjw].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 
//(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương 
//tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: 
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;BAA62945"
//b.	Nhận một chuỗi ngẫu nhiên từ server
//Ví dụ: dgUOo ch2k22ldsOo
//c.	Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện của chúng và gửi lên server
//Ví dụ: d:2,O:2,o:2,2:3,
//d.	Đóng kết nối và kết thúc chương trình.
public class Client {

    public static void main(String[] args) {
        String request = "B21DCCN603;goaZSnjw";
        int port = 2208;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.write(request);
            out.newLine();
            out.flush();

            String s = in.readLine();
            System.out.println(s);

            Map<Character, Integer> m = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetterOrDigit(s.charAt(i))) {
                    if (m.containsKey(s.charAt(i))) {
                        int cnt = m.get(s.charAt(i)) + 1;
                        m.put(s.charAt(i), cnt);
                    } else {
                        m.put(s.charAt(i), 1);
                    }
                }
            }

            String ans = "";
            for (int i = 0; i < s.length(); i++) {
                if (m.containsKey(s.charAt(i)) && m.get(s.charAt(i)) > 1) {
                    ans += s.charAt(i) + ":" + m.get(s.charAt(i)) + ",";
                    m.put(s.charAt(i), 0);
                }
            }
            
            System.out.println(ans);
            out.write(ans);
            out.newLine();
            out.flush();
            out.close();
            in.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}
