package TCPObjectStream;

import TCP.Product;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

//[Mã câu hỏi (qCode): 37yfAzC2].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 
//Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng 
//đối tượng (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:
//
//Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, int discount) 
//và private static final long serialVersionUID = 20231107;
//
//a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1E08CA31"
//
//b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.
//
//c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng các chữ số 
//trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính discount và gửi lên đối tượng nhận được lên server.
//
//d. Đóng kết nối và kết thúc chương trình.
public class client {

    public static int setDiscount(double price) {
        int discount = 0;
        int tmp = (int) price;
        String s = Integer.toString(tmp);

        for (int i = 0; i < s.length(); i++) {
            discount += Integer.parseInt(Character.toString(s.charAt(i)));
        }
        return discount;
    }

    public static void main(String[] args) {
        String request = "B21DCCN234;n0Tz1v5Y";
        String host = "203.162.10.109";
        int port = 2209;

        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            
            os.writeObject(request);
            os.flush();

            Product product = (Product) is.readObject();
            System.out.println(product);
//            Object receiverObject = is.readObject();
//            Product product = (Product) receiverObject;
//            System.out.println(product);

            product.setDiscount(setDiscount(product.getPrice()));
            os.writeObject(product);
            os.close();
            socket.close();

            System.out.println(product);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
