/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Timer;

import java.util.TimerTask;
import real_estate.Corridor;
import real_estate.SubCorridor;

/**
 *
 * @author sabavija
 */
public class Corridor_timer extends TimerTask {

    
    private SubCorridor corridor;
    boolean timer_running;

    public Corridor getCorridor() {
        return corridor;
    }

    public void setCorridor(SubCorridor corridor) {
        this.corridor = corridor;
    }

    public boolean isTimer_running() {
        return timer_running;
    }

    public void setTimer_running(boolean timer_running) {
        this.timer_running = timer_running;
    }
    

    
    public Corridor_timer(SubCorridor corridor, boolean is_running) {
        this.corridor = corridor;
        timer_running = is_running;
    }
    
    
    @Override
    public void run() {
        corridor.getLight_unit().setEnabled(false);
        corridor.setPresence(false);
        corridor.getHotel().balancePower();
        System.out.println(corridor.getHotel().toString());
    }
    
}
