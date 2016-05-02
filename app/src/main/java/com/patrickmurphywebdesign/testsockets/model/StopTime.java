package com.patrickmurphywebdesign.testsockets.model;

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
        return getHour() + ":" + getMinute() +" "+ getAMPM();
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
