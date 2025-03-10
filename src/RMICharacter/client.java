/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMICharacter;

import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.HashMap;

public class client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

        String studentCode = "B21DCCN234";
        String qCode = "HRvjJvEG";

        String s = characterService.requestCharacter(studentCode, qCode);

        System.out.println(s);

        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int total = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            int currentValue = romanMap.get(c);

            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
             prevValue = currentValue;
        }
        
        System.out.println(total);
        
        String ans = Integer.toString(total);
        
        characterService.submitCharacter(studentCode, qCode, ans);
    }
}
