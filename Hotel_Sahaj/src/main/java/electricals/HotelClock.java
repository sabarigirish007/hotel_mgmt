/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricals;

import Model.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sabavija
 */
public class HotelClock {
    private long offset_seconds; 

    public HotelClock(Date curr_app_time) {
        Date global_time = java.util.Calendar.getInstance().getTime();
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        curr_app_time = copyTimeToDate(global_time, curr_app_time);
        offset_seconds = (curr_app_time.getTime() - global_time.getTime())/1000;
        
    }
    /**
 * Copy only the time of one date to the date of another date.
 */
public static Date copyTimeToDate(Date date, Date time) {
    Calendar t = Calendar.getInstance();
    t.setTime(time);

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
    c.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
    c.set(Calendar.SECOND, t.get(Calendar.SECOND));
    c.set(Calendar.MILLISECOND, t.get(Calendar.MILLISECOND));
    return c.getTime();
}
public Date get_time() {
    return new Date( System.currentTimeMillis() + (offset_seconds * 1000));
}

public boolean isNight() {
    Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
    
    calendar.add(Calendar.SECOND, (int)offset_seconds);
    Date curr_time = calendar.getTime();
    //new Date( System.currentTimeMillis() + (offset_seconds * 1000));
    
    if (curr_time.getHours() > Constants.NIGHT_START_HOUR || curr_time.getHours() < Constants.DAY_START_HOUR)  {
        return true;
    } else {
        return false;
    }
}
public boolean isDay() {
    return !isNight ();
}
    
    
}
