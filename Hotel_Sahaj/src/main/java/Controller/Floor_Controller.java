/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SensorData;
import real_estate.Corridor;
import real_estate.Floor;

/**
 *
 * @author sabavija
 */
public class Floor_Controller {
    private Floor floor;

    public Floor_Controller(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
    
    public void processTrigger(SensorData sensor_data) {
        Corridor corridor;
        
        corridor = floor.getCorridor(sensor_data.getCorridor_no(), sensor_data.isIsmain_corridor());
        if (sensor_data.isMovemment_seen()) {
            corridor.setPresence();
        } else {
            corridor.setAbsence();
        }
        floor.balancePower();
    }
    
}
