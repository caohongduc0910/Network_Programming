/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPCharacterStream2;

//[Mã câu hỏi (qCode): FRoeZsyA].  [Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai]
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;DE0C2BF0"
//b.	Nhận lần lượt hai chuỗi ngẫu nhiên từ server
//c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, yêu cầu giữ nguyên thứ tự xuất hiện của ký tự. 
//      Gửi chuỗi đã được xử lý lên server.
//d.	Đóng kết nối và kết thúc chương trình
public class client {

    public static void main(String[] args) {
        String request = "B21DCCN179;FRoeZsyA";
        int port = 2208;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.write(request);
            out.newLine();
            out.flush();

            String s1 = in.readLine();
            System.out.println(s1);
            String s2 = in.readLine();
            System.out.println(s2);
            String s = "";

            for (int i = 0; i < s1.length(); i++) {
                if (!s2.contains(Character.toString(s1.charAt(i)))) {
                    s += s1.charAt(i);
                }
            }
            
            System.out.println(s);
            
            out.write(s);
            out.newLine();
            out.flush();
            out.close();
            in.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
