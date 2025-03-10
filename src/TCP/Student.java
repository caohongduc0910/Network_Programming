/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.Serializable;

/**
 *
 * @author ADMINN
 */
public class Student implements Serializable{
    private int id;
    private String code;
    private float gpa;
    private String gpaLetter;
    private static final long serialVersionUID = 20151107;

    public Student(int id, String code, float gpa) {
        this.id = id;
        this.code = code;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public float getGpa() {
        return gpa;
    }

    public String getGpaLetter() {
        return gpaLetter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", code=" + code + ", gpa=" + gpa + ", gpaLetter=" + gpaLetter + '}';
    }
    
}
