/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIByte3;

import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

//[Mã câu hỏi (qCode): k4gQs0hr].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
//Giao diện từ xa:
//public interface ByteService extends Remote {
//public byte[] requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
//}
//Trong đó:
//•	Interface ByteService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, đại diện cho một chuỗi văn bản ASCII.
//b. Thực hiện mã hóa Caesar cho mảng dữ liệu nhị phân bằng cách dịch chuyển mỗi byte trong mảng đi một số bước cố định trong bảng mã ASCII. 
//Số bước dịch chuyển là số ký tự ASCII trong mảng dữ liệu.
//    Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111] (tương ứng với chuỗi "Hello"), 
//chương trình sẽ thực hiện mã hóa Caesar với độ dịch là 5. Kết quả mã hóa là mảng [77, 108, 113, 113, 116], tương ứng với chuỗi "Mlqqt".
//c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được mã hóa bằng Caesar trở lại server.
//d. Kết thúc chương trình client.
public class client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String studentCode = "B21DCCN603";
        String qCode = "k4gQs0hr";
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        byte[] receive = byteService.requestData(studentCode, qCode);

        int minLower = (int) 'a';
        int minUpper = (int) 'A';
        int maxLower = (int) 'z';
        int maxUpper = (int) 'Z';

        ArrayList<Integer> arr = new ArrayList<>();

        String ans = "";
        int ceasar = receive.length;
        for (int x : receive) {
            if (x >= minLower && x <= maxLower) {
                x += ceasar;
                if (x > maxLower) {
                    x = x - 256;
                }
                char letter = (char) x;
                ans += letter;
            } else if (x >= minUpper && x <= maxUpper) {
                x += ceasar;
                if (x > maxUpper) {
                    x = x - 256;
                }
                char letter = (char) x;
                ans += letter;
            }
            System.out.println(x);
            arr.add(x);
        }
        System.out.println(ans);
        byte[] sending = new byte[receive.length];
        for(int i = 0; i < receive.length; i++) {
            int x = arr.get(i);
            sending[i] = (byte) x;
        }
//        System.out.println(minLower);
        byteService.submitData(studentCode, qCode, sending);
    }
}
