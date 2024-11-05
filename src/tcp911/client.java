/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp911;

//[Mã câu hỏi: 911] Một chương trình (tạm gọi là server) được triển khai tại địa chỉ 203.162.10.109 (hỗ trợ thời gian giao tiếp tối đã cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối với server trên tại cổng 2206, sử dụng luồng byte dữ liệu (DataInputStream/ DataOutputStream) để trao đổi thông tin theo thứ tự:

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "MaSV;MaCauhoi". Ví dụ: "B16DCCN999;701"
//b.	Nhận lần lượt 2 số nguyên a và b từ server
//c.	Thực hiện tính toán ước chung lớn nhất, bội chung nhỏ nhất, tổng, tích và gửi lần lượt từng giá trị số nguyên theo đúng thứ tự lên server.
//d.	Đóng kết nối và kết thúc
public class client {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int multiple(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        
        String host = "localhost";
        int port = 2206;
        String request = "B21DCCN234;911";

        try {
            
            Socket socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(request);
            out.flush();
            
            int a = in.readInt();
            int b = in.readInt();

            System.out.println(gcd(a, b));
            out.writeInt(gcd(a, b));
            out.flush();
            System.out.println(lcm(a, b));
            out.writeInt(lcm(a, b));
            out.flush();
            System.out.println(sum(a, b));
            System.out.println(multiple(a, b));
        } catch (Exception e) {
        }
    }
}
