/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT;

import Controller.Central_Controller;
import Model.SensorData;
import Operations.BuildHotel;
import UI.Current_Time;
import UI.HotelFloorplan;
import UI.SensorInput;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import real_estate.Hotel;

/**
 *
 * @author sabavija
 */
public class Interupts_UT {
    private static Hotel hotel;
    
    public static void main(String[] args) {
       
        HotelFloorplan hotel_plan = new HotelFloorplan();
        SensorInput sensor = new SensorInput();
        Current_Time time_ip = new Current_Time();
        Date ip_time = get_random_time();
        
        
        
        BuildHotel builder = new BuildHotel(ip_time);
        hotel =  builder.build(2, 2, 2); 
        hotel.setInitialState();
        
        //intiial state
        System.out.println("IT is " +(hotel.getClock().isDay()?"DAY":"NIGHT"));
        System.out.println(hotel.toString());
        Central_Controller controller = new Central_Controller(hotel);
        
        for (int idx=0; idx < 4 ; idx ++ ) {
            SensorData data = getSensorData(idx+1);
            controller.processTrigger(data);
            System.out.println(hotel.toString());
            
            try
                {
                    Thread.sleep(1000*10);
                }
            catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
        }
    
    }
    private static SensorData getSensorData(int ut_no) {
        SensorData rt_data =null ;
        switch(ut_no) {
            case 1:
                System.out.println("Case 1 floor:1 maincorridor:1 presence true ");
                rt_data = new SensorData(1,true,1,true);
                break;
            case 2:
                System.out.println("Case 2 floor:2 maincorridor:1 presence true ");
                rt_data = new SensorData(2,true,1,true);
                break;
            case 3:
                System.out.println("Case 1 floor:1 Subcorridor:1 presence true ");
                rt_data = new SensorData(1,false,1,true);
                break;
            case 4:
                System.out.println("Case 2 floor:2 Subcorridor:1 presence true ");
                rt_data = new SensorData(2,false,1,true);
                break;
            
        }
        return rt_data;
    }     
    private static Date get_random_time() {
        int max = 100000, min = -10000;
        Random random = new Random(); 
        int offset = random.nextInt(max - min) + max;
        
        return new Date( System.currentTimeMillis() + (offset * 1000));
    }
    
    public static void UT_init() {
        
    }
}
