/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real_estate;

import electricals.AC;
import electricals.TubeLight;

/**
 *
 * @author sabavija
 */
public class MainCorridor extends Corridor {
    private AC ac_unit;
    private TubeLight light_unit;
    

    public MainCorridor(int id, Hotel hotel) {
        super(id, hotel);
        ac_unit = new AC();
        light_unit = new TubeLight();
        super.setPresence(false);
    }

    public AC getAc_unit() {
        return ac_unit;
    }

    public void setAc_unit(AC ac_unit) {
        this.ac_unit = ac_unit;
    }

    public TubeLight getLight_unit() {
        return light_unit;
    }

    public void setLight_unit(TubeLight light_unit) {
        this.light_unit = light_unit;
    }

    @Override
    public void setPresence() {
                

        // lights and ac are always on here so no change 
    }
    
    @Override
    public void setAbsence() {
                

        // lights and ac are always on here so no change 
    }

    @Override
    public int gettotalpowerutil() {
        return ac_unit.gettotalpowerutil() + light_unit.gettotalpowerutil();
    }

    @Override
    public void setDay() {
        light_unit.setEnabled(false);
        ac_unit.setEnabled(true);
    }

    @Override
    public void setNight() {
        light_unit.setEnabled(true);
        ac_unit.setEnabled(true);
    }
    
    @Override
    public Hotel getHotel() {
        return hotel;
    }
    
    @Override
    public String toString() {
        String  ret = new String();
        ret = ret.concat("Main corridor "+this.getId()+" Light "+ this.getId()
        + ":"+ (light_unit.isEnabled()? "ON": "OFF") + "  AC: "+ (ac_unit.isEnabled()? "ON": "OFF")  );
        return ret;
    }
   
    
}
