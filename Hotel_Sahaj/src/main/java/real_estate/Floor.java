/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package real_estate;

import Controller.Floor_Controller;
import java.util.List;

/**
 *
 * @author sabavija
 */
public class Floor {
    private List<MainCorridor> mainCorridorList;
    private List<SubCorridor> subCorridorList;
    private Floor_Controller floor_controller;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public Corridor getCorridor (int id, boolean is_main_corridor) {
        
        if (is_main_corridor) {
            for (Corridor corridor : mainCorridorList){
                if (corridor.getId() == id) {
                    return corridor;
                }   
            }   
        } else {
            for (Corridor corridor : subCorridorList) {
                if (corridor.getId() == id) {
                    return corridor;
                }
            }
        }
        System.out.println("corridor not found for id "+ id + "is main" +is_main_corridor);
        return null;
    }

    public Floor_Controller getFloor_controller() {
        return floor_controller;
    }

    public void setFloor_controller(Floor_Controller floor_controller) {
        this.floor_controller = floor_controller;
    }
    
    public List<MainCorridor> getMainCorridorList() {
        return mainCorridorList;
    }

    public Floor(int id ,List<MainCorridor> mainCorridorList, List<SubCorridor> subCorridorList) {
        this.id = id;
        this.mainCorridorList = mainCorridorList;
        this.subCorridorList = subCorridorList;
        floor_controller = new Floor_Controller(this);
    }

    public void setMainCorridorList(List<MainCorridor> mainCorridorList) {
        this.mainCorridorList = mainCorridorList;
    }

    public List<SubCorridor> getSubCorridorList() {
        return subCorridorList;
    }

    public void setSubCorridorList(List<SubCorridor> subCorridorList) {
        this.subCorridorList = subCorridorList;
    }
    
    public int gettotalpowerutil() {
        int total_power = 0;
        for (Corridor corridor : mainCorridorList){
            total_power += corridor.gettotalpowerutil();
        }
        for (Corridor corridor : subCorridorList){
            total_power += corridor.gettotalpowerutil();
        }
        return total_power;
    }
    
    public int getMaxPower() {
        return (((mainCorridorList.size()) * 15) + ((subCorridorList.size()) * 10)); 
    }
    
    public void balancePower() {
        boolean modified = false;
        if (this.gettotalpowerutil() > this.getMaxPower()) {   
            for (SubCorridor corridor : subCorridorList) {
                if (corridor.getAc_unit().isEnabled() && (!corridor.isPresence())) {
                    corridor.getAc_unit().setEnabled(false);
                    modified = true;
                    break;
                }
            }
            if (!modified) {
                for (SubCorridor corridor : subCorridorList) {
                    if (corridor.getAc_unit().isEnabled()) {
                        corridor.getAc_unit().setEnabled(false);
                        modified = true;
                        break;
                    }
                }
                
            }
            
        } else if (this.getMaxPower() - this.gettotalpowerutil() >= 10 ) {
            
            for (SubCorridor corridor : subCorridorList) {
                if (!corridor.getAc_unit().isEnabled()) {
                    corridor.getAc_unit().setEnabled(true);
                    modified = true;
                    break;
                }
            }
            
        }
        
    }
    
    public void setDay() {
        for (Corridor corridor : mainCorridorList){
            corridor.setDay();
            this.balancePower();
        }
        for (Corridor corridor : subCorridorList){
            corridor.setDay();
            this.balancePower();
        }
    }
    
    public void setNight() {
        for (Corridor corridor : mainCorridorList){
            corridor.setNight();
            this.balancePower();
        }
        for (Corridor corridor : subCorridorList){
            corridor.setNight();
            this.balancePower();
        }
    }
    
    @Override
    public String toString() {
       String ret = new String();
       
       ret = ret.concat("\t Floor "+ this.getId() +"\n");
       for (Corridor corridor : mainCorridorList){
           
           ret = ret.concat(corridor.toString() + "\n");
        }
        for (Corridor corridor : subCorridorList){
            ret = ret.concat(corridor.toString() + "\n");
            
        }
       return ret;
    }
            
}
