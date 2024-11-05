/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp721;

/**
 *
 * @author ADMINN
 */
/*
 * Server program for processing client requests
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // Đọc mã sinh viên và mã câu hỏi từ client
                String request = in.readLine();
                System.out.println("Yêu cầu từ client: " + request);

                // Tạo chuỗi ngẫu nhiên để gửi cho client
                String randomString = "dgU0o ch2k22lds0o";
                System.out.println("Gửi chuỗi ngẫu nhiên cho client: " + randomString);

                // Gửi chuỗi ngẫu nhiên cho client
                out.write(randomString);
                out.newLine();
                out.flush();
                
                String request2 = in.readLine();
                System.out.println("KQ từ client: " + request2);

                // Đóng kết nối với client sau khi xử lý xong
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

