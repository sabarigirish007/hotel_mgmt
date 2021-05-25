/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorInputTest;


import Controller.Central_Controller;
import Model.SensorData;
import Operations.BuildHotel;
import Operations_Test.BuildOpertaion_Test;
import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;
import real_estate.Hotel;

/**
 *  %%%%%%%% IMPORTANT NOTICE %%%%%%%%%%%
 * Testing this file will have delays to due to induced delays to check timers
 *
 * @author sabavija
 */
public class SensorIpTest extends TestCase {
    private Hotel hotel;
    Date ip_time;
    BuildHotel builder;
    Central_Controller controller;
    
    protected void setUp() throws Exception {
	super.setUp();
        ip_time = BuildOpertaion_Test.get_random_time();
        Calendar t = Calendar.getInstance();
        t.set(Calendar.HOUR_OF_DAY, 22);
        System.out.println(t.getTime());
        builder = new BuildHotel(t.getTime());
        hotel =  builder.build(2, 2, 2);
        assertTrue("It is not  Night", hotel.getClock().isNight());
        hotel.setInitialState();
        controller = new Central_Controller(hotel);
                
    }
    protected void tearDown() throws Exception {
	super.tearDown();
    }
    
    // TC1
    public void testMainCorridorPresence() {
        //before trigger
        assertFalse("Main corridor 1 presece is ON", hotel.getfloor(1).getCorridor(1, true).isPresence());
        //Light should be On
        assertTrue("Main corridor 1 light is OFF", hotel.getfloor(1).getCorridor(1, true).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Main corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, true).getAC().isEnabled());
        //floor 1 main corridor 1 presence true
        SensorData sen_data = new SensorData(1,true,1,true);
        controller.processTrigger(sen_data);
        //Presence should be set
        assertTrue("Main corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, true).isPresence());
        //Light should be On
        assertTrue("Main corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, true).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Main corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, true).getAC().isEnabled());
        
    }
    
    // TC2 -- sub monitor presence enables light and balances power with another sub corridor
    public void testSubCorridorPresece() {
        //before trigger
        assertFalse("Sub corridor 1 presece is ON", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be On
        assertFalse("Sub corridor 1 light is ON", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        //floor 1 sub corridor 1 presence true
        SensorData sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        //Presence should be set
        assertTrue("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be On
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        //power consumption should be less or eq to max
        assertTrue("Power used > Power max", hotel.getfloor(1).getMaxPower() >= hotel.getfloor(1).gettotalpowerutil());
        // Ac of Subcorridor 2 should be shut
        assertFalse("SubCorridor 2 AC not shut",hotel.getfloor(1).getCorridor(2, false).getAC().isEnabled() );
    }
    
    // TC3 -- sub monitor presence twice enables light and and switches off one AC only
    public void testSubCorridor2Presece() {
        
        SensorData sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        
        //Second trigger presnce in corridor2 
        sen_data = new SensorData(1,false,2,true);
        controller.processTrigger(sen_data);
        
        assertTrue("Sub corridor 2 Presence is OFF", hotel.getfloor(1).getCorridor(2, false).isPresence());
        
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(2, false).getTubeLight().isEnabled());
        
        //AC should be OFF for sub corridor 2
        assertFalse("Sub corridor 2 AC is ON", hotel.getfloor(1).getCorridor(2, false).getAC().isEnabled());
        
        //AC should be on for sub corridor 1
        assertTrue("Sub corridor 2 AC is ON", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        
        //power shoudl be still balanced
        assertTrue("Power used > Power max", hotel.getfloor(1).getMaxPower() >= hotel.getfloor(1).gettotalpowerutil());
        
    }
    //TC4 -- presnce followerd by absence in Main corridor
    public void testMainCorridorAbsence() {
        
        //presenece
        SensorData sen_data = new SensorData(1,true,1,true);
        controller.processTrigger(sen_data);
        //Presence should be set
        assertTrue("Main corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, true).isPresence());
        //Light should be On
        assertTrue("Main corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, true).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Main corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, true).getAC().isEnabled());
        //absence
        sen_data = new SensorData(1,true,1,false);
        controller.processTrigger(sen_data);
        //Presence should be unset
        assertFalse("Main corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, true).isPresence());
        //Light should be On
        assertTrue("Main corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, true).getTubeLight().isEnabled());
        //AC should be ON
        assertTrue("Main corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, true).getAC().isEnabled());    
    }
    
    //TC5 -- subcoridor presence with 60 sec absence -- restart the AC that was switched off 
    public void testSubCorridorAbsense() throws InterruptedException {
        //floor 1 sub corridor 1 presence true
        SensorData sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        sen_data = new SensorData(1,false,1,false);
        controller.processTrigger(sen_data);
        //timer not expired
        //Presence should be still set
        assertTrue("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be still On
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        Thread.sleep(70 * 1000);
        // after 1 min 
        //Presence should be  unset
        assertFalse("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertFalse("Sub corridor 1 Light is ON", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        // it should restart the AC of corridor 2 too
        assertTrue("Sub corridor 2 AC is OFF", hotel.getfloor(1).getCorridor(2, false).getAC().isEnabled());
      
    }
    
    //TC6 -- subcoridor presence with 5 sec absence and presnce again
    public void testSubCorridorAbsenseStop() throws InterruptedException {
        //floor 1 sub corridor 1 presence true
        SensorData sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        sen_data = new SensorData(1,false,1,false);
        controller.processTrigger(sen_data);
      
        Thread.sleep(5 * 1000);
        // after 5 sec
        //presence
        sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        //Presence should be  unset
        assertTrue("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        // after 60 seconds also it should be same
         Thread.sleep(60 * 1000);
         
         assertTrue("Sub corridor 1 Presence is ON", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
      
    }
    
    //TC6 -- subcoridor presence with 5 sec absence and presnce again and absence again to restart the clock
    public void testSubCorridorAbsenseRestart() throws InterruptedException {
        //floor 1 sub corridor 1 presence true
        SensorData sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        sen_data = new SensorData(1,false,1,false);
        controller.processTrigger(sen_data);
      
        Thread.sleep(5 * 1000);
        // after 5 sec
        //presence
        sen_data = new SensorData(1,false,1,true);
        controller.processTrigger(sen_data);
        //Presence should be  unset
        assertTrue("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
         Thread.sleep(10 * 1000);
        sen_data = new SensorData(1,false,1,false);
        controller.processTrigger(sen_data);
         assertTrue("Sub corridor 1 Presence is ON", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertTrue("Sub corridor 1 Light is OFF", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        
        Thread.sleep(70 * 1000);
        //Presence should be  unset
        assertFalse("Sub corridor 1 Presence is OFF", hotel.getfloor(1).getCorridor(1, false).isPresence());
        //Light should be off
        assertFalse("Sub corridor 1 Light is ON", hotel.getfloor(1).getCorridor(1, false).getTubeLight().isEnabled());
        //AC should be still ON
        assertTrue("Sub corridor 1 AC is OFF", hotel.getfloor(1).getCorridor(1, false).getAC().isEnabled());
        // it should restart the AC of corridor 2 too
        assertTrue("Sub corridor 2 AC is OFF", hotel.getfloor(1).getCorridor(2, false).getAC().isEnabled());
        
      
    }
    
    
    
    
}
