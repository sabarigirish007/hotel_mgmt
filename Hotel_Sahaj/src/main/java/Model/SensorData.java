/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sabavija
 */
public class SensorData {
    private int floor_no;
    private boolean ismain_corridor ;
    private int corridor_no;
    private boolean movemment_seen;
    private boolean is_exit_cmd;

    public boolean isIs_exit_cmd() {
        return is_exit_cmd;
    }

    public void setIs_exit_cmd(boolean is_exit_cmd) {
        this.is_exit_cmd = is_exit_cmd;
    }

    public int getFloor_no() {
        return floor_no;
    }

    public void setFloor_no(int floor_no) {
        this.floor_no = floor_no;
    }

    public boolean isIsmain_corridor() {
        return ismain_corridor;
    }

    public void setIsmain_corridor(boolean ismain_corridor) {
        this.ismain_corridor = ismain_corridor;
    }

    public int getCorridor_no() {
        return corridor_no;
    }

    public void setCorridor_no(int corridor_no) {
        this.corridor_no = corridor_no;
    }

    public boolean isMovemment_seen() {
        return movemment_seen;
    }

    public void setMovemment_seen(boolean movemment_seen) {
        this.movemment_seen = movemment_seen;
    }

    public SensorData(int floor_no, boolean ismain_corridor, int corridor_no, boolean movemment_seen) {
        this.floor_no = floor_no;
        this.ismain_corridor = ismain_corridor;
        this.corridor_no = corridor_no;
        this.movemment_seen = movemment_seen;
    }
    
    
}
