/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp912;

//[Mã câu hỏi: 912] Một chương trình (tạm gọi là server) được triển khai tại địa chỉ 203.162.10.109 (hỗ trợ thời gian giao tiếp tối đã cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối với server trên tại cổng 2206, sử dụng luồng byte dữ liệu (BufferedWrite/ BufferedReader) để trao đổi thông tin theo thứ tự:
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "MaSV;MaCauhoi". Ví dụ: "B16DCCN999;701"
//b.	Nhận 1 chuỗi ngẫu nhiên từ server 
//c.	Thực hiện tách chuỗi đã nhận thành 2 chuỗi và gửi lần lượt theo thứ tự lên server
//- Chuỗi thứ nhất gồm các ký tự và số (loại bỏ các ký tự đặc biệt)
//- Chuỗi thứ 2 gồm các ký tự đặc biệt
//d.	Đóng kết nối và kết thúc
public class client {

    public static void main(String[] args) {

        try {

            String request = "B21DCCN234;912";
            String host = "localhost";
            int port = 2206;

            Socket socket = new Socket(host, port);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            output.write(request);
            output.newLine();
            output.flush();
            
            
            String s = input.readLine();
            
            String result1 = "";
            String result2 = "";
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i))) {
                    result1 += s.charAt(i);
                } else {
                    result2 += s.charAt(i);
                }
            }

            System.out.println(result1);
            System.out.println(result2);
            
            output.write(result1);
            output.newLine();
            output.flush();
            output.write(result2);
            output.newLine();
            output.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
