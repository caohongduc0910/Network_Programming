package TCPDataStream2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

//[Mã câu hỏi (qCode): ZbDNZ2R3].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 
//(hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng chương trình client tương 
//tác với server bằng các byte stream (DataInputStream/DataOutputStream) để trao đổi thông tin theo trình tự sau:
//
//a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B10DCCN000;A1B2C3D4".
//
//b. Nhận từ server một mảng chứa n số nguyên, với n được gửi từ máy chủ. Ví dụ: Server gửi mảng [5, 9, 3, 6, 8].
//
//c. Tính tổng, trung bình cộng, và phương sai của mảng. Gửi kết quả lần lượt lên server dưới dạng số nguyên và float. 
//Ví dụ, gửi lên lần lượt: 31, 6.2, 4.5599995.
//
//d. Đóng kết nối và kết thúc chương trình.
public class client {

    public static void main(String[] args) {
        String request = "B21DCCN179;ZbDNZ2R3";
        int port = 2207;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(request);
            dos.flush();

            int cnt = dis.readInt();

            ArrayList<Integer> arr = new ArrayList();
            int x = 0;
            while (true) {
                arr.add(dis.readInt());
                cnt--;
                if (cnt == 0) {
                    break;
                }
            }

            int sum = 0;
            float tbc = 0;
            float preSum = 0;
            float ps = 0;

            for (int n : arr) {
                sum += n;
            }

            tbc = (float) sum / arr.size();

            for (int n : arr) {
                preSum += (n - tbc) * (n - tbc);
            }

            ps = preSum / arr.size();
            
            dos.writeInt(sum);
            dos.flush();
            dos.writeFloat(tbc);
            dos.flush();
            dos.writeFloat(ps);
            dos.flush();
            
            System.out.println(sum);
            System.out.println(tbc);
            System.out.println(ps);
            
            dos.close();
            dis.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}
