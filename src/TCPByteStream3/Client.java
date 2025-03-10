/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPByteStream3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

//[Mã câu hỏi (qCode): KiJZ4dC3].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 
//(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;FF49DC02"
//b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự ","
//Ex: 1,3,9,19,33,20
//c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và hai giá trị lớn nhất tạo nên khoảng cách đó. 
//Gửi lên server chuỗi gồm "khoảng cách nhỏ nhất, số thứ nhất, số thứ hai". Ex: 1,19,20
//d.	Đóng kết nối và kết thúc
public class Client {
    
    public static void main(String[] args) {
        String request = "B21DCCN603;KiJZ4dC3";
        int port = 2206;
        String host = "203.162.10.109";
        
        try {
            Socket socket = new Socket(host, port);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            out.write(request.getBytes());
            out.flush();
            
            byte[] receiving = new byte[1024];
            int numberOfByte = in.read(receiving);
            String s = new String(receiving, 0, numberOfByte);
            String ans = "";
            
            ArrayList<Integer> arr = new ArrayList<>();
            String number[] = s.split(",");
            for (int i = 0; i < number.length; i++) {
                arr.add(Integer.parseInt(number[i]));
            }
            
            Collections.sort(arr, Collections.reverseOrder());
            int minn = 100000;
            for (int i = 1; i < arr.size(); i++) {
                int distance = arr.get(i - 1) - arr.get(i);
                minn = distance < minn ? distance : minn;
            }
            
            ans += minn + ",";
            for (int i = 1; i < arr.size(); i++) {
                int distance = arr.get(i - 1) - arr.get(i);
                if (distance == minn) {
                    ans += arr.get(i) + "," + arr.get(i - 1);
                }
            }
            
            System.out.println(ans);
            
            out.write(ans.getBytes());
            out.flush();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
        }
    }
}
