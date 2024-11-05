/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp701;

//[Mã câu hỏi: 701] Một chương trình (tạm gọi là server) được triển khai tại địa chỉ 203.162.10.109 (hỗ trợ thời gian giao tiếp tối đã cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối với server trên tại cổng 2206, sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;701"
//b.	Nhập dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi kỹ tự ",". Ví dụ: 1, 3, 9, 19, 33, 20
//c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và 2 giá trị lớn nhất tạo nên khoảng cách đó. Ví dụ 1, 19, 20
//d.	Đóng kết nối và kết thúc

import tcp912.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class server {

    public static void main(String[] args) {
        int port = 2206;
        try {
            // Khởi tạo ServerSocket lắng nghe tại cổng 2206
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server đang chạy, chờ kết nối từ client...");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Đã kết nối với client!");

                // Khởi tạo luồng đọc và ghi dữ liệu
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                // Đọc mã sinh viên và mã câu hỏi từ client
                byte[] length1 = new byte[1024];
                
                int number = in.read(length1);
                String request = new String(length1, 0, number);
                
                System.out.println("Yêu cầu từ client: " + request);

                // Tạo chuỗi ngẫu nhiên để gửi cho client
                String randomString = "1, 3, 9, 19, 33, 20";
                System.out.println("Gửi chuỗi ngẫu nhiên cho client: " + randomString);

                // Gửi chuỗi ngẫu nhiên cho client
                out.write(randomString.getBytes());
                out.flush();
                
                byte[] receiver = new byte[1024];
                int numberOfBytes = in.read(receiver);
                String result = new String(receiver, 0, numberOfBytes);
                
                System.out.println("KQ từ client: " + result);
                

                // Đóng kết nối với client sau khi xử lý xong
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

