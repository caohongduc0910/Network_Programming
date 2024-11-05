/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp911;

/**
 *
 * @author ADMINN
 */
/*
 * Server program for processing client requests
 */
import tcp721.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                // Đọc mã sinh viên và mã câu hỏi từ client
                String request = in.readUTF();
                System.out.println("Yêu cầu từ client: " + request);

                // Tạo chuỗi ngẫu nhiên để gửi cho client
                int a = 24;
                int b = 16;

                // Gửi chuỗi ngẫu nhiên cho client
                out.writeInt(a);
                out.flush();
                
                out.writeInt(b);
                out.flush();
                
                int result1 = in.readInt();
                System.out.println("KQ từ client: " + result1);
                
                int result2 = in.readInt();
                System.out.println("KQ từ client: " + result2);

                // Đóng kết nối với client sau khi xử lý xong
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

