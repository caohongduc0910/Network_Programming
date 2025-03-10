/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIData;

import RMI.ByteService;
import RMI.DataService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;

public class client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataService = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B21DCCN234";
        String qCode = "KEsnkopk";

        Object result = dataService.requestData(studentCode, qCode);
        System.out.println(result);
        
        int n = (int) result;
        List<Integer> arr = new ArrayList<>();
        
        for(int i = 1; i < Math.sqrt(n); i++) {
            arr.add(i * i);
        }
        
        dataService.submitData(studentCode, qCode, arr);
    }
}
