/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPObjectStream2;

import TCP.Student;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//[Mã câu hỏi (qCode): CvC8up0J].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 
//Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng(ObjectOutputStream/ObjectInputStream) 
//theo kịch bản dưới đây:
//Biết lớp TCP.Student gồm các thuộc tính (id int,code String, gpa float, gpaLetter String) và 
//private static final long serialVersionUID = 20151107;
//a.	Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D059A3F"
//b.	Nhận một đối tượng là thể hiện của lớp TCP.Student từ server
//c.	Chuyển đổi giá trị điểm số gpa của đối tượng nhận được sang dạng điểm chữ và gán cho gpaLetter.  Nguyên tắc chuyển đổi
//i.	3.7 – 4 -> A
//ii.	3.0 – 3.7 -> B
//iii.	2.0 – 3.0 -> C
//iv.	1.0 – 2.0 -> D
//v.	0 – 1.0 -> F
//d.     Gửi đối tượng đã được xử lý ở trên lên server.
//e.     Đóng kết nối và kết thúc chương trình
public class Client {

    public static void main(String[] args) {
        String request = "B21DCCN690;CvC8up0J";
        int port = 2209;
        String host = "203.162.10.109";

        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(request);
            os.flush();
            
            Student student = (Student) is.readObject();
            System.out.println(student);
            
            if(student.getGpa()>=3.7){
                student.setGpaLetter("A");
            }
            else if(student.getGpa()>=3){
                student.setGpaLetter("B");
            }
            else if(student.getGpa()>=2){
                student.setGpaLetter("C");
            }
            else if(student.getGpa()>=1){
                student.setGpaLetter("D");
            }
            else {
                student.setGpaLetter("F");
            }
            
            System.out.println(student);
            
            os.writeObject(student);
            os.flush();
            os.close();
            is.close();
            socket.close();
            
        } catch (Exception e) {
        }
    }
}
