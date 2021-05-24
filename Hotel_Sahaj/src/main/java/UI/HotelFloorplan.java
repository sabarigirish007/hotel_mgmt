/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Operations.BuildHotel;
import java.util.Date;
import real_estate.Hotel;
import java.util.Scanner;

/**
 *
 * @author sabavija
 */
public class HotelFloorplan {

    private Hotel hotel;
    private static Scanner SCANNER;
    
    public Hotel getHotelplan(Scanner SCANNER, Date app_time) {
        this.SCANNER = SCANNER;
        System.out.println("Number of floors: ");
        throwExceptionForInvalidValue();
        int floorCount = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Main Corridors per floor: ");
        throwExceptionForInvalidValue();
        int mainCorridorCount = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Sub Corridors per floor: ");
        throwExceptionForInvalidValue();
        int subCorridorCount = Integer.parseInt(SCANNER.nextLine());
        BuildHotel builder = new BuildHotel(app_time);
        hotel =  builder.build(floorCount, mainCorridorCount, subCorridorCount); 
        return hotel;
    }
    
    private static void throwExceptionForInvalidValue() {
        while (!SCANNER.hasNextInt()) {
                System.err.println("Please enter an integer.");
                SCANNER.nextLine();
        }
    }
    
}
