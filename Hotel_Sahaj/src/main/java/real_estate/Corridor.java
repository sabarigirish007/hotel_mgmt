/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real_estate;

import Timer.Corridor_timer;
import java.util.Timer;

/**
 *
 * @author sabavija
 */
public abstract class Corridor {
    private int id;
    private boolean presence;
    private Corridor_timer timer_task;
    private Timer timer;
    public Hotel hotel;

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Corridor_timer getTimer_task() {
        return timer_task;
    }

    public void setTimer_task(Corridor_timer timer_task) {
        this.timer_task = timer_task;
    }
    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Corridor(int id , Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
        

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public abstract Hotel getHotel();
    public abstract void setPresence();
    public abstract void setAbsence();
    public abstract int gettotalpowerutil();
    public abstract void setDay();
    public abstract void setNight();
    
}
