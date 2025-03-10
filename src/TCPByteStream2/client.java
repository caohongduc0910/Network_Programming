/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPByteStream2;

//[Mã câu hỏi (qCode): 1YLs5DdE].  
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server sử dụng các luồng byte (InputStream/OutputStream) 
//để trao đổi thông tin theo thứ tự:
//    a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;10048F28".
//    b. Nhận chuỗi ký tự từ server. Ví dụ: "abcabcbb"
//    c. Tìm và gửi lên server chuỗi con dài nhất từ chuỗi nhận được mà không có ký tự lặp lại theo format "longestsubstring;length". 
//Ví dụ: "abc;3".
//    d. Đóng kết nối và kết thúc chương trình.
public class client {

    public static void main(String[] args) {
        String request = "B21DCCN690;1YLs5DdE";
        int port = 2206;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            os.write(request.getBytes());
            os.flush();

            byte[] receiving = new byte[1024];
            int numberOfReceiving = is.read(receiving);
            String s = new String(receiving, 0, numberOfReceiving);

            System.out.println(s);

            String maxString = "";
            String subString = "";
            int cnt = 0;
            int maxx = 0;

            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i; j < s.length(); j++) {
                    if (!subString.contains(Character.toString(s.charAt(j)))) {
                        subString += s.charAt(j);
                        cnt++;
                    } else {
                        if (cnt > maxx) {
                            maxx = cnt;
                            maxString = subString;
                        }
                        cnt = 0;
                        subString = "";
                        break;
                    }
                }
            }

            if (cnt > maxx) {
                maxString = subString;
                maxx = cnt;
            }

            String ans = maxString + ";" + maxx;

            os.write(ans.getBytes());
            os.flush();

            os.close();
            is.close();
            socket.close();

        } catch (Exception e) {
        }
    }
}
