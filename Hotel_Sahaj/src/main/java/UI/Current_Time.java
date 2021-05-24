/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabavija
 */
public class Current_Time {
    public Date getCurrentTime(Scanner SCANNER)   {
        System.out.println("Enter first time (HH:mm:ss): ");
        
        DateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date d1 = null;
        d1 = getValidInput(SCANNER);
      
        return d1;
    } 
    
    private Date getValidInput(Scanner SCANNER) {
        boolean is_valid = false;
        Date d1 = null;
        while(!is_valid) {
        String time = SCANNER.nextLine();
        
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                d1 = sdf.parse(time);
                System.out.println("correct time");
                is_valid = true;
            } catch (ParseException ex) {     
                System.out.println("Enter valid time format: (HH:mm:ss)");
            }
        }
        return d1;
    }
}
