/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIByte;

import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author ADMINN
 */

import java.util.Arrays;
public class client {

    public static byte[] runLengthEncoding(byte[] data) {
        byte[] result = new byte[data.length * 2];
        int count = 1;
        int resultIndex = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] == data[i - 1]) {
                count++;
            } else {
                result[resultIndex++] = data[i - 1];
                result[resultIndex++] = (byte) count;
                count = 1;
            }
        }
        result[resultIndex++] = data[data.length - 1];
        result[resultIndex++] = (byte) count;
        return Arrays.copyOf(result, resultIndex);
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
            ByteService byteService = (ByteService) registry.lookup("RMIByteService");

            String studentCode = "B21DCCN690";
            String qCode = "u0aOMlbz";

            byte[] reqData = byteService.requestData(studentCode, qCode);
            byte[] result = runLengthEncoding(reqData);
            byteService.submitData(studentCode, qCode, result);
        } catch (Exception e) {
        }
    }
}
