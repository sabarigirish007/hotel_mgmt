/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real_estate;

import Timer.Corridor_timer;
import electricals.AC;
import electricals.TubeLight;
import java.util.Timer;

/**
 *
 * @author sabavija
 */
public class SubCorridor extends Corridor {
    private AC ac_unit;
    
    public SubCorridor(int id, Hotel hotel) {
        super(id, hotel);
        ac_unit = new AC();
        light_unit = new TubeLight();
        super.setTimer_task(new Corridor_timer(this, false));
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
    private TubeLight light_unit;

    @Override
    public void setPresence() {
        if (super.getTimer_task().isTimer_running()) {
            super.getTimer().cancel();
            super.getTimer_task().setTimer_running(false);
        }
        light_unit.setEnabled(true);
        this.setPresence(true);        
    }
    
    @Override
    public void setAbsence() {
        if (!super.getTimer_task().isTimer_running()) {
            super.setTimer(new Timer());
            super.setTimer_task(new Corridor_timer(this, true));
            super.getTimer().schedule(super.getTimer_task(), 60 * 1000);
        }
        
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
        if(this.isPresence()) {
            light_unit.setEnabled(true);
        }  
        ac_unit.setEnabled(true);
    }
    
    @Override
    public String toString() {
        String  ret = new String();
        ret = ret.concat("Sub corridor "+this.getId()+" Light "+ this.getId()
        + ":"+ (light_unit.isEnabled()? "ON": "OFF") + "  AC: "+ (ac_unit.isEnabled()? "ON": "OFF")  );
        return ret;
    }

    @Override
    public Hotel getHotel() {
        return hotel;
    }

    public  TubeLight getTubeLight(){
        return light_unit;
    }
    public  AC getAC() {
        return ac_unit;
    }
    
}
