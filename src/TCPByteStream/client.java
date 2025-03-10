package TCPByteStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

//[Mã câu hỏi (qCode): X33cAyzn].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 
//(thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác 
//tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
//a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;A63D9404".
//b. Nhận dữ liệu từ server là một chuỗi các số nguyên được sắp xếp ngẫu nhiên, các số được phân tách nhau bởi ký tự ",". 
//Ví dụ: "2,15,4,3,6,8,10,7,1".
//c. Sắp xếp tăng dần các giá trị chẵn và sau đó tăng dần các giá trị lẻ trong dãy số. Ví dụ: "[2, 4, 6, 8, 10];[1, 3, 7, 15]". 
//Gửi chuỗi được sắp xếp này lên server.
//d. Đóng kết nối và kết thúc chương trình.

public class client {

    public static void main(String[] args) {
        String request = "B21DCCN234;X33cAyzn";
        int port = 2206;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            os.write(request.getBytes());
            os.flush();

            byte[] receivedByte = new byte[1024];
            int numberOfReceivedByte = is.read(receivedByte);
            String receivedString = new String(receivedByte, 0, numberOfReceivedByte);
//            System.out.println(receivedString);

            String numbers[] = receivedString.split(",");

            ArrayList<Integer> evens = new ArrayList<>();
            ArrayList<Integer> odds = new ArrayList<>();

            for (String x : numbers) {
                int number = Integer.parseInt(x);
                if (number % 2 == 0) {
                    evens.add(number);
                } else {
                    odds.add(number);
                }
            }

            Collections.sort(odds);
            Collections.sort(evens);

            String s = "[";
            for (int i = 0; i < evens.size(); i++) {
                if (i != evens.size() - 1) {
                    s += evens.get(i) + ", ";
                } else {
                    s += evens.get(i) + "];[";
                }
            }
            
            for (int i = 0; i < odds.size(); i++) {
                if (i != odds.size() - 1) {
                    s += odds.get(i) + ", ";
                } else {
                    s += odds.get(i) + "]";
                }
            }
            
            os.write(s.getBytes());
            os.flush();
            os.close();
            is.close();
            socket.close();
            
//            System.out.println(s);

        } catch (Exception e) {
        }
    }
}
