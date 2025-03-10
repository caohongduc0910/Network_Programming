/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPString3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

//[Mã câu hỏi (qCode): S4PecjEm].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. 
//Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;5B35BCC1”
//b.	Nhận thông điệp từ server theo định dạng “requestId;data” 
//-	requestId là một chuỗi ngẫu nhiên duy nhất
//-	data là chuỗi dữ liệu cần xử lý
//c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
//i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
//ii.	Các ký tự còn lại của chuỗi là in thường
//Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng “requestId;data”
//d.	Đóng socket và kết thúc chương trình
public class Client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN603;S4PecjEm";
        int port = 2208;
        InetAddress host = InetAddress.getByName("203.162.10.109");

        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sending = request.getBytes();
            DatagramPacket sendingString = new DatagramPacket(sending, sending.length, host, port);
            socket.send(sendingString);

            byte[] receiving = new byte[1024];
            DatagramPacket receivingString = new DatagramPacket(receiving, receiving.length, host, port);
            socket.receive(receivingString);
            String s = new String(receivingString.getData(), 0, receivingString.getLength());
            System.out.println(s);

            String temp[] = s.split(";");
            String ans = temp[0] + ";";
            String str[] = temp[1].split("\\s+");
            for (String x : str) {
                String copy = "";
                for (int i = 0; i < x.length(); i++) {
                    if (i == 0) {
                        copy += Character.toUpperCase(x.charAt(i));
                    }
                    else copy += Character.toLowerCase(x.charAt(i));
                }
                ans += copy + " ";
            }

            System.out.println(ans);
            byte[] sending2 = ans.getBytes();
            DatagramPacket sendingAnswer = new DatagramPacket(sending2, sending2.length, host, port);
            socket.send(sendingAnswer);
            
            socket.close();

//            socket.send(receivingString);
        } catch (Exception e) {
        }
    }
}
