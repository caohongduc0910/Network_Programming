/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp701;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

//[Mã câu hỏi: 701] Một chương trình (tạm gọi là server) được triển khai tại địa chỉ 203.162.10.109 (hỗ trợ thời gian giao tiếp tối đã cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối với server trên tại cổng 2206, sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;701"
//b.	Nhập dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi kỹ tự ",". Ví dụ: 1, 3, 9, 19, 33, 20
//c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và 2 giá trị lớn nhất tạo nên khoảng cách đó. Ví dụ 1, 19, 20
//d.	Đóng kết nối và kết thúc
public class client {

    public static void main(String[] args) {
        String request = "B21DCCN234;701";
        int port = 2206;
        String host = "localhost";

        try {
            
            Socket socket = new Socket(host, port);
            
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            out.write(request.getBytes());
            out.flush();
            
            
            byte [] receiver = new byte[1024];
            int numberOfBytes = in.read(receiver);
            String s = new String(receiver, 0, numberOfBytes);
            

            String arr[] = s.split(", ");

            //cho các phần tử vào ArrayList -> Sort ngược
            ArrayList<Integer> arrList = new ArrayList<>();
            for (String c : arr) {
                arrList.add(Integer.valueOf(c));
            }
            
            Collections.sort(arrList, Collections.reverseOrder());
            
            

            int miN = 100000;
            int length = arrList.size();

            //tìm hiệu bé nhất
            for (int i = 0; i < length - 1; i++) {
                miN = Math.min(miN, arrList.get(i) - arrList.get(i + 1));
            }

            String ans = miN + ", ";

            //nếu bắt gặp hiệu bé nhất thì dừng luôn for
            for (int i = 0; i < length - 1; i++) {
                if (arrList.get(i) - arrList.get(i + 1) == miN) {
                    ans += arrList.get(i + 1) + ", " + arrList.get(i);
                    break;
                }
            }

            System.out.println(ans);
            out.write(ans.getBytes());
            out.flush();
            
        } catch (Exception e) {
        }
    }
}
