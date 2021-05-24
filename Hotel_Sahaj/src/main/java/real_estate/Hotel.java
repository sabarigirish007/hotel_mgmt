/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real_estate;

import Controller.Central_Controller;
import electricals.HotelClock;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sabavija
 */
public class Hotel {
    private List<Floor> floorList;
    private Central_Controller main_controller;
    private HotelClock clock;

    public HotelClock getClock() {
        return clock;
    }

    public void setClock(HotelClock clock) {
        this.clock = clock;
    }

    public Hotel(List<Floor> floorList, Date date) {
        this.floorList = floorList;
        main_controller = new Central_Controller(this);
        clock = new HotelClock(date);
    }
    
    public void setInitialState() {
        if (clock.isDay()) {
            this.setDay();
        } else {
            this.setNight();
        }
    }
    
    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
    
    public Floor getfloor(int fl_id) {
        Floor ret_val = null;
        for (Floor floor : floorList){
                if (floor.getId() == fl_id) {
                    ret_val = floor;
                    break;
                }   
            }
        return ret_val;
    }
    
    public void setDay() {
        for (Floor floor : floorList){
            floor.setDay();
        }
    }
    
    public void setNight() {
        for (Floor floor : floorList){
            floor.setNight();
        }
    }
    
    @Override
    public String toString() {
        String ret =new String("");
        for (Floor floor : floorList){
            //ret.concat("\t\t\t Floor "+ floor.getId());
            ret =  ret.concat(floor.toString());
        }
        return ret;
    }
    
    public void balancePower() {
        for (Floor floor : floorList){
            floor.balancePower();
        }
        
    }
    
    
}
