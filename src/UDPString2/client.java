/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPString2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

//      [Mã câu hỏi (qCode): qqaVOHkP].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. 
//      Yêu cầu là xây dựng một chương trình client tương tác với server kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
//b.	Nhận thông điệp từ server theo định dạng “requestId; data” 
//-	requestId là một chuỗi ngẫu nhiên duy nhất
//-	data là chuỗi dữ liệu đầu vào cần xử lý
//      Ex: “requestId;Qnc8d5x78aldSGWWmaAAjyg3”
//c.	Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng “requestId;
//      ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó” 
//      ví dụ: “requestId;8:4,9,”
//d.	Đóng socket và kết thúc chương trình
public class client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN179;qqaVOHkP";
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

            String str = new String(receivingString.getData(), 0, receivingString.getLength());
            System.out.println(str);
            String tmp[] = str.split(";");
            String s = tmp[1];
            String ans = tmp[0] + ";";
            int maxx = 1;
            char maxVal = 0;

            Map<Character, Integer> m = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (m.containsKey(s.charAt(i))) {
                    int cnt = m.get(s.charAt(i)) + 1;
                    maxx = maxx < cnt ? cnt : maxx;
                    m.put(s.charAt(i), cnt);
                } else {
                    m.put(s.charAt(i), 1);
                }
            }

            for (int i = 0; i < s.length(); i++) {
                if (m.containsKey(s.charAt(i)) && m.get(s.charAt(i)) == maxx) {
                    maxVal = s.charAt(i);
                    ans += maxVal + ":";
                    break;
                }
            }

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == maxVal) {
                    ans += (i + 1) + ",";
                }
            }

            byte[] sending2 = ans.getBytes();
            DatagramPacket sendingAnswer = new DatagramPacket(sending2, sending2.length, host, port);
            socket.send(sendingAnswer);
            socket.close();

            System.out.println(ans);
        } catch (Exception e) {
        }
    }
}
