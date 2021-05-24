/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel_sahaj;

import Controller.Central_Controller;
import Model.SensorData;
import UI.Current_Time;
import UI.HotelFloorplan;
import UI.SensorInput;
import java.util.Date;
import java.util.Scanner;
import real_estate.Hotel;

/**
 *
 * @author sabavija
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
    System.out.println(" Welcome to the Hotel management APP");
    HotelFloorplan hotel_plan = new HotelFloorplan();
    SensorInput sensor = new SensorInput();
    Current_Time time_ip = new Current_Time();
    Date ip_time = time_ip.getCurrentTime(SCANNER);
    Hotel hotel  = hotel_plan.getHotelplan(SCANNER, ip_time);
    hotel.setInitialState();
    System.out.println(hotel.toString());
    Central_Controller controller = new Central_Controller(hotel);
    while (true) {
        SensorData data = sensor.getSensorInput(SCANNER);
        if (data.isIs_exit_cmd()) {
            break;
        } else {
            controller.processTrigger(data);
            System.out.println(hotel.toString());
        }
    }
    
    }
}
