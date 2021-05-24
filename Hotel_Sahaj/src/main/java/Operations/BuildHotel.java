/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import java.util.ArrayList;
import java.util.Date;
import real_estate.Floor;
import real_estate.Hotel;
import real_estate.MainCorridor;
import real_estate.SubCorridor;

/**
 *
 * @author sabavija
 */
public class BuildHotel {
    private Hotel hotel;
    

    public Hotel getHotel() {
        return hotel;
    }
    
    public BuildHotel(Date app_time) {
        hotel = new Hotel(new ArrayList<Floor>(), app_time);    
    }
    
    
    public Hotel build(int floor_cnt, int main_corridor_cnt, int sub_corridor_cnt) {
        buildFloors(floor_cnt, main_corridor_cnt, sub_corridor_cnt);
        return hotel;
    }
    
    
    private void buildFloors(int floor_cnt, int main_corridor_cnt, int sub_corridor_cnt) {
        for (int cnt = 0; cnt < floor_cnt; cnt++) {
            Floor floor = new Floor(cnt+1 ,new ArrayList<MainCorridor>(), new ArrayList<SubCorridor>());
            buildMainCorridor(floor, main_corridor_cnt);
            buildSubCorridor(floor, sub_corridor_cnt);
            hotel.getFloorList().add(floor);
        }
    }
    
    
    private void buildMainCorridor(Floor floor, int main_corridor_cnt) {
        for (int cnt = 0; cnt < main_corridor_cnt; cnt++) {
            floor.getMainCorridorList().add(new MainCorridor(cnt+1, hotel));
        }
    }
    
    
    private void buildSubCorridor(Floor floor, int sub_corridor_cnt) {
        for (int cnt = 0; cnt < sub_corridor_cnt; cnt++) {
            floor.getSubCorridorList().add(new SubCorridor(cnt+1, hotel));
        }
    }
    
}
