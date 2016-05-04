package com.patrickmurphywebdesign.BusCentral.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by turnerp on 4/28/2016.
 */
public class StopTime {
    private boolean isCC;
    private int hour;
    private int minute;

    public StopTime(boolean isCC, int hour, int minute) {
        this.isCC = isCC;
        this.hour = hour;
        this.minute = minute;
    }

    public Map<TimeUnit, Long> getRelativeTimeMap(){
        Date date1 = new Date(); // now

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), this.hour, this.minute);

        Date date2 = cal.getTime();

        long diffInMillies = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
        long milliesRest = diffInMillies;
        for ( TimeUnit unit : units ) {
            long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit,diff);
        }

        return result;
    }

    public String getRelativeTime(){
        Map<TimeUnit, Long> time = getRelativeTimeMap();

        String returnString = "";

        if(time.get(TimeUnit.HOURS) > 0){
            if(time.get(TimeUnit.HOURS) == 1){
                returnString += time.get(TimeUnit.HOURS) + " Hr ";
            }else {
                returnString += time.get(TimeUnit.HOURS) + " Hrs ";
            }
        }

        if(time.get(TimeUnit.HOURS) <= 2 && time.get(TimeUnit.MINUTES) > 0){
            returnString += time.get(TimeUnit.MINUTES) + " Min";
        }

        if(time.get(TimeUnit.HOURS) <= 0 && time.get(TimeUnit.MINUTES) <= 0 && time.get(TimeUnit.SECONDS) > 0){
            returnString = "Any Second";
        }

        return returnString;
    }

    public boolean isCC() {
        return isCC;
    }

    public String getAMPM(){
        if(hour>=12)
            return "PM";
        else
            return "AM";
    }

    public String getFormattedTime(){
        return getHour() + ":" + getMinuteFormatted() +" "+ getAMPM();
    }

    public String getDirection(){
        if(isCC)
            return "Counter Clockwise";
        else
            return "Clockwise";
    }

    public int get24Hour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getMinuteFormatted(){
        if(getMinute() == 0){
            return "00";
        }else if (getMinute() == 60){
            return "00";
            // hour + 1
        }else{
            return getMinute() + "";
        }
    }

    public int getHour(){
        if(hour == 12 || hour == 0)
            return 12;
        else
            return hour%12;
    }

    public String toString(){
        if(isCC){
            return "Counter Clockwise "+ getHour() + ":"+getMinute();
        }else{
            return "Clockwise "+ getHour() + ":"+getMinute();
        }
    }
}
