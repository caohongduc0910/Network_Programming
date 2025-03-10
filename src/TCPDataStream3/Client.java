/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPDataStream3;

//[Mã câu hỏi (qCode): 8B0MD6tV].  Một chương trình servercho phép kết nối qua TCP tại cổng 2207 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

//Yêu cầu là xây dựng chương trình client tương tác với server bằng các byte stream (DataInputStream/DataOutputStream) 
//để trao đổi thông tin theo trình tự sau:
//
//a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B10DCCN000;0D135D6A".
//
//b. Nhận từ server một số nguyên n, là số lần tung xúc xắc. Ví dụ: Nếu bạn nhận được n = 21 từ máy chủ, 
//có nghĩa bạn sẽ nhận giá trị tung xúc xắc 21 lần.
//
//b. Nhận từ server các giá trị sau mỗi lần tung xúc xắc. Ví dụ: Server gửi lần lượt 21 giá trị là 1,6,4,4,4,3,2,6,3,4,5,4,5,2,4,5,4,6,1,5,5
//
//c. Tính xác suất xuất hiện của các giá trị [1,2,3,4,5,6] khi tung xúc sắc và gửi lần lượt xác suất này (dưới dạng float) 
//lên server theo đúng thứ tự. Ví dụ gửi lên server lần lượt 6 giá trị là 0.0952381, 0.0952381, 0.0952381, 0.33333334, 0.232209524, 0.14285715
//
//d. Đóng kết nối và kết thúc chương trình.
public class Client {

    public static void main(String[] args) {
        String request = "B21DCCN603;8B0MD6tV";
        int port = 2207;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(request);
            dos.flush();

            int n = dis.readInt();
            System.out.println(n);
            HashMap<Integer, Integer> m = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int x = dis.readInt();
                if (m.containsKey(x)) {
                    int cnt = m.get(x) + 1;
                    m.put(x, cnt);
                } else {
                    m.put(x, 1);
                }
            }

            for (int i = 1; i <= 6; i++) {
                int appear = m.get(i);
                float prob = (float) appear / n;
                dos.writeFloat(prob);
                dos.flush();
            }
            
            dos.close();
            dis.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}
