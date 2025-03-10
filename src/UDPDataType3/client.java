/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDPDataType3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
//[Mã câu hỏi (qCode): CpII3Jyr].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. 
//Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;371EA16D”
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId; n; A1,A2,...An”, với
//-	requestId là chuỗi ngẫu nhiên duy nhất
//-	n là một số ngẫu nhiên nhỏ hơn 100.
//-            A1, A2 ... Am với m <= n là các giá trị nguyên liên tiếp, nhỏ hơn hoặc bằng n và không trùng nhau.
//Ví dụ: requestId;10;2,3,5,6,9
//c.	Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
//Ví dụ: requestId;1,4,7,8,10
//d.	Đóng socket và kết thúc chương trình.

public class client {

    public static void main(String[] args) throws UnknownHostException {
        String request = ";B21DCCN690;CpII3Jyr";
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
            String s = new String(receivingString.getData(), 0, receivingString.getLength());

//            String s = "requestId;10;2,3,5,6,9";

            System.out.println(s);

            String[] temp = s.split(";");
            String ans = temp[0] + ";";
            int maxx = Integer.parseInt(temp[1]);

            String number[] = temp[2].split(",");
            ArrayList<Integer> arr = new ArrayList<>();
            for (String x : number) {
                arr.add(Integer.parseInt(x));
            }

            for (int i = 1; i <= maxx; i++) {
                if (!arr.contains(i)) {
                    ans += i + ",";
                }
            }
            String str  = ans.substring(0, ans.length() - 1);
            System.out.println(str);
            byte[] sending2 = str.getBytes();
            DatagramPacket sendingAnswer = new DatagramPacket(sending2, sending2.length, host, port);
            socket.send(sendingAnswer);
            socket.close();
        } catch (Exception e) {
        }
    }
}
