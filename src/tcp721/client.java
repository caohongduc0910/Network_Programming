/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;

//[Mã câu hỏi: 721] Một chương trình (tạm gọi là server) được triển khai tại địa chỉ 203.162.10.109 (hỗ trợ thời gian giao tiếp tối đã cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối với server trên tại cổng 2206, sử dụng luồng byte dữ liệu (BufferedWriter/ BufferedReader) để trao đổi thông tin theo thứ tự:
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "MaSV;MaCauhoi". Ví dụ: "B16DCCN999;721"
//b.	Nhận 1 chuỗi ngẫu nhiên từ server 
//Ví dụ: dgU0o ch2k22lds0o
//c.	Thực hiện liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện cuar chúng.
//Ví dụ: d:2,0:2,o:2,2:3,
//d.	Đóng kết nối và kết thúc   < ĐÁP ÁN BỊ SAI, YC CẬP NHẬT LẠI---- ĐA CẬP NHẬT>
public class client {

    public static void main(String[] args) {

        String resquest = "B21DCCN234;721";
        int port = 2206;
        String host = "localhost";

        try {
            Socket socket = new Socket(host, port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.write(resquest);
            out.newLine();
            out.flush();

            String s = in.readLine();

//            String s = "dgU0o ch2k22lds0o";
            Map<Character, Integer> m = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetterOrDigit(s.charAt(i))) {
                    if (m.containsKey(s.charAt(i))) {
                        int cnt = m.get(s.charAt(i));
                        m.put(s.charAt(i), ++cnt);
                    } else {
                        m.put(s.charAt(i), 1);
                    }
                }
            }

            String result = "";

            for (int i = 0; i < s.length(); i++) {
                if (m.containsKey(s.charAt(i)) && m.get(s.charAt(i)) > 1) {
//                    System.out.print(s.charAt(i) + ":" + m.get(s.charAt(i)) + ",");
                    result += s.charAt(i) + ":" + m.get(s.charAt(i)) + ",";
                    m.put(s.charAt(i), 0);
                }
            }
            
            System.out.println(result);

            out.write(result);
            out.newLine();
            out.flush();
            out.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
