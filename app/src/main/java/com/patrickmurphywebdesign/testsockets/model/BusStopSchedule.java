package com.patrickmurphywebdesign.testsockets.model;

import com.patrickmurphywebdesign.testsockets.RouteProperties;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class BusStopSchedule {
    private BusStop stop;
    private LinkedList<StopTime> stops;
    private HashMap<String, Integer> ccRoute;
    private HashMap<String, Integer> cwRoute;

    public BusStopSchedule(BusStop stop) {
        this.stop = stop;
        stops = new LinkedList<StopTime>();

        // data about the bus schedule
        RouteProperties rp = new RouteProperties();
        ccRoute = rp.getCcBusRoute();
        cwRoute = rp.getCwBusRoute();

        // current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // current min and hours
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        // is it saturday
        boolean isSaturday = cal.get(Calendar.DAY_OF_WEEK) == 7;
        // is it sunday
        boolean isSunday = cal.get(Calendar.DAY_OF_WEEK) == 1;
        // is it a weekend
        boolean isWeekend = isSaturday || isSunday;

        int[] endTime = new int[2];
        endTime[0] = (ccRoute.get(RouteProperties.BUS_END_HOUR));
        endTime[1] = (ccRoute.get(RouteProperties.BUS_END_MIN));
        // if it its weekend
        if(isWeekend) {
            // end time is cc end time
            endTime[0] = (ccRoute.get(RouteProperties.BUS_WEEKEND_END_HOUR));
            endTime[1] = (ccRoute.get(RouteProperties.BUS_WEEKEND_END_MIN));
        }

        if(min < stop.getCwOffset() && (cwRoute.get(RouteProperties.BUS_RUNNING_FLAG) == 1) && hour >= cwRoute.get(RouteProperties.BUS_START_HOUR) && hour <= cwRoute.get(RouteProperties.BUS_END_HOUR)){
            stops.add(new StopTime(false, hour, stop.getCwOffset()));
        }
        if(min < stop.getCcOffset() && hour >= ccRoute.get(RouteProperties.BUS_START_HOUR) && hour <= endTime[0]){
            stops.add(new StopTime(true, hour, stop.getCcOffset()));
        }

        int numberOfHoursLeft = endTime[0] - hour;
        for(int i = 1; i <= numberOfHoursLeft; i++){
            int newHour = i + hour;
            if(newHour <= cwRoute.get(RouteProperties.BUS_END_HOUR) && cwRoute.get(RouteProperties.BUS_RUNNING_FLAG) == 1){
                if(hour == cwRoute.get(RouteProperties.BUS_END_HOUR) && stop.getCwOffset() <= cwRoute.get(RouteProperties.BUS_END_MIN)){
                    stops.add(new StopTime(false, newHour, stop.getCwOffset()));
                }else if(hour != cwRoute.get(RouteProperties.BUS_END_HOUR)){
                    stops.add(new StopTime(false, newHour, stop.getCwOffset()));
                }
            }
            if(newHour <= endTime[0]){
                if(newHour == endTime[0] && stop.getCcOffset() <= endTime[1]){
                    stops.add(new StopTime(true, newHour, stop.getCcOffset()));
                }else if(hour != endTime[0]){
                    stops.add(new StopTime(true, newHour, stop.getCcOffset()));
                }
            }
        }
    }

    public HashMap<String, Integer> getCcRoute() {
        return ccRoute;
    }

    public HashMap<String, Integer> getCwRoute() {
        return cwRoute;
    }

    public StopTime getNextTime(){
        return stops.peek();
    }

    public LinkedList<StopTime> getStops(){
        return stops;
    }

}
