/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SensorData;
import real_estate.Hotel;

/**
 *
 * @author sabavija
 */
public class Central_Controller {
    private Hotel hotel;

    public Central_Controller(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public void processTrigger(SensorData sensor_data) {
        if (hotel.getClock().isNight()) {
            hotel.getfloor(sensor_data.getFloor_no()).getFloor_controller().processTrigger(sensor_data);
        } else {
            //nothing needs to be done in day time
        }
    }
    
}
