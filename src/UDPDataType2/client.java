/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPDataType2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

//[Mã câu hỏi (qCode): y6w2NcY8].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được 
//sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
//Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu xây dựng chương trình client 
//trao đổi thông tin với server theo kịch bản mô tả dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;825EE3A7"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strEncode;s".
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	strEncode là chuỗi thông điệp bị mã hóa
//•	s là số nguyên chứa giá trị độ dịch của mã
//c.	Giải mã tìm thông điệp ban đầu và gửi lên server theo định dạng “requestId;strDecode”
//d.	Đóng socket và kết thúc chương trình.
public class client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN179;y6w2NcY8";
        int port = 2207;
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

            String temp[] = str.split(";");
            String s = temp[1];
            int k = Integer.parseInt(temp[2]);
            String answer = temp[0] + ";";

            for (int i = 0; i < s.length(); i++) {
                if (Character.isUpperCase(s.charAt(i))) {
                    int x = (int) s.charAt(i);
                    int minn = (int) ('A') - 1;
                    int maxx = (int) ('Z');
                    if (x + k > maxx) {
                        char c = (char) (x + k - maxx + minn);
                        answer += c;
                    } else {
                        char c = (char) (x + k);
                        answer += c;
                    }
                } else if (Character.isLowerCase(s.charAt(i))) {
                    int x = (int) s.charAt(i);
                    int minn = (int) ('a') - 1;
                    int maxx = (int) ('z');
                    if (x + k > maxx) {
                        char c = (char) (x + k - maxx + minn);
                        answer += c;
                    } else {
                        char c = (char) (x + k);
                        answer += c;
                    }
                }
            }
            System.out.println(answer);

            byte[] sending2 = answer.getBytes();
            DatagramPacket sendingAnswer = new DatagramPacket(sending2, sending2.length, host, port);
            socket.send(sendingAnswer);
            socket.close();

        } catch (Exception e) {
        }
    }
}
