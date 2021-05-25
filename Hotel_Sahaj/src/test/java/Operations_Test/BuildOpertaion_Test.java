/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations_Test;

import Operations.BuildHotel;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import junit.framework.TestCase;
import real_estate.Hotel;

/**
 *
 * @author sabavija
 */
public class BuildOpertaion_Test extends TestCase {
    private Hotel hotel;
    Date ip_time;
    BuildHotel builder;
    
    protected void setUp() throws Exception {
	super.setUp();
        ip_time = get_random_time();
                
    }
    protected void tearDown() throws Exception {
	super.tearDown();
    }
    
    public static Date get_random_time() {
        int max = 100000, min = -10000;
        Random random = new Random(); 
        int offset = random.nextInt(max - min) + max;
        
        return new Date( System.currentTimeMillis() + (offset * 1000));
    }
    
    public void test_constructor () {
        builder = new BuildHotel(ip_time);
        assertNotNull(builder.getHotel().getClock().get_time().toString(), "the Hotel and timer is not initiated");
    }
    
    public void testFloorMainSubCoordinator() {
        builder = new BuildHotel(ip_time);
        hotel =  builder.build(2, 3, 4);
        assertTrue("floor size is not 2",hotel.getFloorList().size()==2);
        assertTrue("Main corridor size is not 3", hotel.getfloor(1).getMainCorridorList().size() == 3);
        assertTrue("Sub corridor size is not 4", hotel.getfloor(1).getSubCorridorList().size() == 4);
        hotel.setInitialState();
    }
    
    public void testSetInitialState() {
        // setting to day
        Calendar t = Calendar.getInstance();
        t.set(Calendar.HOUR_OF_DAY, 12);
        System.out.println(t.getTime());
        builder = new BuildHotel(t.getTime());
        hotel =  builder.build(2, 3, 4);
        assertTrue("It is not day", hotel.getClock().isDay());
        hotel.setInitialState();
        //chk if main corridor light is off
        assertFalse("Main Light ON on DAY ", hotel.getfloor(2).getCorridor(1, true).getTubeLight().isEnabled());
        //chk if main corridor AC is ON
        assertTrue("Main Ac OFF on DAY", hotel.getfloor(2).getCorridor(1, true).getAC().isEnabled());
        //chk if Sub corridor Light is of and Ac is ON 
        assertFalse("Sub Light ON on DAY ", hotel.getfloor(2).getCorridor(1, false).getTubeLight().isEnabled());
        assertTrue("Sub Ac OFF on DAY", hotel.getfloor(2).getCorridor(1, false).getAC().isEnabled());
        

        //set it to night
        t.set(Calendar.HOUR_OF_DAY, 22);
        builder = new BuildHotel(t.getTime());
        hotel =  builder.build(2, 3, 4);
        assertTrue("It is not day", hotel.getClock().isNight());
        hotel.setInitialState();
        
        //chk if main corridor light is off
        assertTrue("Main Light OFF on DAY ", hotel.getfloor(2).getCorridor(1, true).getTubeLight().isEnabled());
        //chk if main corridor AC is ON
        assertTrue("Main Ac OFF on Night", hotel.getfloor(2).getCorridor(1, true).getAC().isEnabled());
        //chk if Sub corridor Light is of and Ac is ON 
        assertFalse("Sub Light ON on Night ", hotel.getfloor(2).getCorridor(1, false).getTubeLight().isEnabled());
        assertTrue("Sub Ac OFF on Night", hotel.getfloor(2).getCorridor(1, false).getAC().isEnabled());
        
    }
    
}
