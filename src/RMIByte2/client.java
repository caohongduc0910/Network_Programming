/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIByte2;

import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Collections;

public class client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        String studentCode = "B21DCCN234";
        String qCode = "0UfveyNs";
        byte[] receivedData = byteService.requestData(studentCode, qCode);

        ArrayList<Integer> arr = new ArrayList<>();
        int k = receivedData[receivedData.length - 1];
        System.out.println(k);
        System.out.println("----------");

        for (int i = 0; i < receivedData.length - 1; i++) {
            int x = receivedData[i];
            System.out.println(x);
            if (!arr.contains(x)) {
                arr.add(x);
            }
        }
        System.out.println("-----------");
        Collections.sort(arr, Collections.reverseOrder());
        System.out.println(arr);
        int maxx = 0;
        int index = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (i == k - 1) {
                maxx = arr.get(i);
            }
        }
        
        for (int i = 0; i < receivedData.length - 1; i++) {
            if(receivedData[i] == maxx) {
                index = i + 1;
                break;
            }
        }
        
        byte[] ans = new byte[2];
        ans[0] = (byte) maxx;
        ans[1] = (byte) index;

        byteService.submitData(studentCode, qCode, ans);

        System.out.println(maxx);

    }
}
