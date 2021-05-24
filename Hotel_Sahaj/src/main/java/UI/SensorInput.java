/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.SensorData;
import java.util.Scanner;

/**
 *
 * @author sabavija
 */


public class SensorInput {
    /* Accepting inputs in the 4 spaced format <floor,main/sub,no,on/off>*/
    public SensorData getSensorInput(Scanner scan) {
        
        boolean exit=false;
        SensorData sendata = null;
        //while  (!exit) {
            System.out.println("Enter Sensor data in format<floor_no,1:main 0:sub corridor, corridor_no, 0: Movement_absent 1: movement_seen");
            sendata = getValidInput(scan);
            if (sendata.isIs_exit_cmd()) {
                System.out.println("exit is set in sen data chk2");
                exit = true;
                
            } 
             
            //throwExceptionForInvalidValue(scan);
            //input = scan.nextLine();
            
        //}
         System.out.println("returning sen_data");
        return sendata;
    }
    
    private static SensorData getValidInput(Scanner scan) {
        String input;
        
        String sensorPattern = "\\d\\,\\d\\,\\d\\,\\d+";
        SensorData sen_data = null; 
        boolean is_valid = false;
        while (!is_valid) {
            input = scan.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                is_valid = true;
            }
            if(!input.matches(sensorPattern)) {
                if (is_valid) {
                    sen_data = new SensorData(0,false,0,false);
                    System.out.println("exit is set in sen data");
                    sen_data.setIs_exit_cmd(true);
                    return sen_data;
                }
                System.err.println("Invalid i/p");
                continue;
            } else {
                String[] p = input.split(",");
                int[] ans = new int[p.length];
                for (int i = 0; i < p.length; i++) {
                    ans[i] = Integer.parseInt(p[i]);
                    System.out.println(ans[i]);
                }
                sen_data = new SensorData(ans[0],ans[1]==1,ans[2],ans[3]==1);
                System.out.println("sen_datat returned");
                return sen_data;
            }            
        }
        return sen_data;
    }
}
