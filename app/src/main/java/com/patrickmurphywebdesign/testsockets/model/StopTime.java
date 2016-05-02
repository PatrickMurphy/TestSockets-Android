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

    public int get24Hour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour(){
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
