package com.patrickmurphywebdesign.BusCentral.controller;

import com.google.android.gms.maps.model.LatLng;
import com.patrickmurphywebdesign.BusCentral.model.BusStop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by turnerp on 4/27/2016.
 */
public class RouteProperties {
    public static final String BUS_WEEKEND_FLAG = "runsOnWeekends";
    public static final String BUS_RUNNING_FLAG = "isRunning";
    public static final String BUS_START_HOUR = "startHour";
    public static final String BUS_START_MIN = "startMin";
    public static final String BUS_END_HOUR = "endHour";
    public static final String BUS_END_MIN = "endMin";
    public static final String BUS_WEEKEND_END_HOUR = "endHourWeekend";
    public static final String BUS_WEEKEND_END_MIN = "endMinWeekend";

    private ArrayList<LatLng> route;
    private ArrayList<BusStop> stops;
    private HashMap<String, Integer> ccBusRoute;
    private HashMap<String, Integer> cwBusRoute;
    private boolean isSaturday;
    private boolean isSunday;
    private boolean isWeekend;
    private boolean busesActive;

    public RouteProperties() {
        route = new ArrayList<LatLng>();
        route.add(new LatLng(46.999734, -120.54473899999999));
        route.add(new LatLng(46.988318, -120.54418099999998));
        route.add(new LatLng(46.98845, -120.53684199999998));
        route.add(new LatLng(46.98348, -120.536295));
        route.add(new LatLng(46.983517, -120.53778599999998));
        route.add(new LatLng(46.984593, -120.53774399999998));
        route.add(new LatLng(46.984571, -120.539943));
        route.add(new LatLng(46.983473, -120.540007));
        route.add(new LatLng(46.983466, -120.54069400000003));
        route.add(new LatLng(46.984578, -120.540705));
        route.add(new LatLng(46.984542, -120.546809));
        route.add(new LatLng(46.98431508126736, -120.5468145254631));
        route.add(new LatLng(46.984271154007516, -120.54690311888317));
        route.add(new LatLng(46.98417599222835, -120.54694879695899));
        route.add(new LatLng(46.983605042123834, -120.54697594113924));
        route.add(new LatLng(46.98357691405645, -120.54577417790983));
        route.add(new LatLng(46.984549, -120.54573700000003));
        route.add(new LatLng(46.984571, -120.544513));
        route.add(new LatLng(46.980757, -120.544556));
        route.add(new LatLng(46.980757, -120.54546800000003));
        route.add(new LatLng(46.984322, -120.54784999999998));
        route.add(new LatLng(46.98523,  -120.54813999999999));
        route.add(new LatLng(46.988194, -120.54827899999998));
        route.add(new LatLng(46.98817190667309, -120.54909456812294));
        route.add(new LatLng(46.988163198588, -120.54931386078272));
        route.add(new LatLng(46.98818376517608, -120.5494902380982));
        route.add(new LatLng(46.98825417296464, -120.54960695833591));
        route.add(new LatLng(46.98838401030581, -120.54967946627045));
        route.add(new LatLng(47.000619, -120.550232));
        route.add(new LatLng(47.00065636608457, -120.5489713644181));
        route.add(new LatLng(47.002785, -120.54903000000002));
        route.add(new LatLng(47.002888, -120.54327999999998));
        route.add(new LatLng(47.00607407703492, -120.543387));
        route.add(new LatLng(47.006088341892735, -120.54724927116393));
        route.add(new LatLng(47.0060282866898, -120.54783949933625));
        route.add(new LatLng(47.0058775411794, -120.54833846130754));
        route.add(new LatLng(47.00572534754284, -120.54867913739963));
        route.add(new LatLng(47.005639, -120.54905200000002));
        route.add(new LatLng(47.005609551164014, -120.54971699999999));
        route.add(new LatLng(47.005625829069544, -120.55039272883607));
        route.add(new LatLng(47.00728842355232, -120.55029626256561));
        route.add(new LatLng(47.00958195226698,  -120.55033927116386));
        route.add(new LatLng(47.01392, -120.550318));
        route.add(new LatLng(47.014066, -120.53184299999998));
        route.add(new LatLng(47.01043, -120.531768));
        route.add(new LatLng(47.01056576420688, -120.52555294477463));
        route.add(new LatLng(47.01092842704461, -120.5255693263893));
        route.add(new LatLng(47.0111421845361, -120.52546923289015));
        route.add(new LatLng(47.01123523539662,  -120.52515456266968));
        route.add(new LatLng(47.01124576471915, -120.52378493254088));
        route.add(new LatLng(47.01135565973344, -120.52342308102038));
        route.add(new LatLng(47.011765407037934, -120.52308926934631));
        route.add(new LatLng(47.01226293569943, -120.52275545767213));
        route.add(new LatLng(47.01241117343869, -120.52270976256568));
        route.add(new LatLng(47.012581356747226, -120.52273916931154));
        route.add(new LatLng(47.01288504361012, -120.5229697417335));
        route.add(new LatLng(47.013079, -120.52291600000001));
        route.add(new LatLng(47.013190274613535, -120.52306090674585));
        route.add(new LatLng(47.0131918204809, -120.52322727116393));
        route.add(new LatLng(47.01310961720308, -120.52326223313526));
        route.add(new LatLng(47.012943288192766, -120.52310407605745));
        route.add(new LatLng(47.01288955862654, -120.52296176686474));
        route.add(new LatLng(47.01258654102922, -120.52274040856355));
        route.add(new LatLng(47.01241125670775, -120.52270751347442));
        route.add(new LatLng(47.01226523329392, -120.52276044907376));
        route.add(new LatLng(47.011759071505594, -120.52309174388301));
        route.add(new LatLng(47.01135532192533, -120.52341767427441));
        route.add(new LatLng(47.01125193584833, -120.52378522090146));
        route.add(new LatLng(47.01123528883314, -120.52515064980696));
        route.add(new LatLng(47.01114192244122, -120.52546590162461));
        route.add(new LatLng(47.01092784941591, -120.52556657672119));
        route.add(new LatLng(47.01056173759977, -120.52555294477463));
        route.add(new LatLng(47.01043, -120.53173500000003));
        route.add(new LatLng(47.00738284730509, -120.53196697668642));
        route.add(new LatLng(47.00748239616301, -120.53264461474987));
        route.add(new LatLng(47.007453, -120.53299099999998));
        route.add(new LatLng(47.00737226427694, -120.533339364418));
        route.add(new LatLng(47.007233, -120.53367700000001));
        route.add(new LatLng(47.0072860648775, -120.53379648528482));
        route.add(new LatLng(47.007394, -120.53380600000003));
        route.add(new LatLng(47.00753880319284, -120.53341585830117));
        route.add(new LatLng(47.0076122749818, -120.53308204299162));
        route.add(new LatLng(47.00762335715593, -120.53264060796926));
        route.add(new LatLng(47.00748354655257, -120.53264576074798));
        route.add(new LatLng(47.00738065036117, -120.53196502331355));
        route.add(new LatLng(47.00312205014143, -120.53177818650823));
        route.add(new LatLng(47.00305029615379, -120.53546874008953));
        route.add(new LatLng(47.00307, -120.53652));
        route.add(new LatLng(47.00304896468994, -120.53546479944032));
        route.add(new LatLng(47.00308280409776, -120.53370149570083));
        route.add(new LatLng(47.00210552208528, -120.53365183250617));
        route.add(new LatLng(47.00200283561396, -120.53364577637194));
        route.add(new LatLng(47.001966, -120.53368799999998));
        route.add(new LatLng(47.00181416631047, -120.53403098805472));
        route.add(new LatLng(47.001647698545504, -120.53434178960129));
        route.add(new LatLng(47.001248909602026, -120.53502776571082));
        route.add(new LatLng(47.000158642511856, -120.5369147020607));
        route.add(new LatLng(46.999975634105866, -120.53736263558199));
        route.add(new LatLng(46.99987957635717, -120.53804261177066));
        route.add(new LatLng(46.99986400623587, -120.5387601388855));
        route.add(new LatLng(46.99980359768565, -120.54109641534421));
        route.add(new LatLng(46.999734, -120.54473899999999));

        stops = new ArrayList<BusStop>();
        stops.add(new BusStop("CWU Library", new LatLng(47.0061666666667,-120.544366666667), true, 58, 28));
        stops.add(new BusStop("CrestView", new LatLng(47.0134666666667,-120.5319), true, 50, 40));
        stops.add(new BusStop("Brooklane", new LatLng(47.012815,-120.522927), true, 45, 43));
        stops.add(new BusStop("CWU SURC", new LatLng(47.0030166666667,-120.535766666667), true, 40, 50));
        stops.add(new BusStop("Safeway", new LatLng(46.9956833333333,-120.544683333333), true, 30, 0));
        stops.add(new BusStop("Fred Meyer", new LatLng(46.99115,-120.549666666667), true, 10, 20));
        stops.add(new BusStop("Super 1", new LatLng(46.9837666666667,-120.54565), true, 15, 15));
        stops.add(new BusStop("Kittitas Valley Healthcare", new LatLng(46.98738979647016,-120.53675651550293)));
        stops.add(new BusStop("Hopesource", new LatLng(46.983543641593315,-120.53779184818268)));
        stops.add(new BusStop("Bi-Mart", new LatLng(46.98347044852351,-120.54009854793549)));
        stops.add(new BusStop("McDonald's", new LatLng(46.9807540456417,-120.54461136460304)));
        stops.add(new BusStop("2nd & Water", new LatLng(46.993372560429336,-120.54982423782349)));
        stops.add(new BusStop("6th & Water", new LatLng(46.99755095004258,-120.55005490779877)));
        stops.add(new BusStop("11th & Main", new LatLng(47.00276799100881,-120.54878890514374)));
        stops.add(new BusStop("11th & D st", new LatLng(47.002885057592806,-120.54331183433533)));
        stops.add(new BusStop("15th & Water", new LatLng(47.006667633525836,-120.5503499507904)));
        stops.add(new BusStop("Helena & Water", new LatLng(47.013906458977274,-120.55027484893799)));
        stops.add(new BusStop("Bright Beginnings", new LatLng(47.01392956346856,-120.54457783699036)));
        stops.add(new BusStop("Airport & Helena", new LatLng(47.013980769429246,-120.53983569145203)));
        stops.add(new BusStop("18th & Brook Ln", new LatLng(47.01058643951037,-120.52565217018127)));
        stops.add(new BusStop("Student Village", new LatLng(47.00735284701488,-120.53374171257019)));
        stops.add(new BusStop("Kamola Hall", new LatLng(46.99980407798805,-120.54086297750473)));
        stops.add(new BusStop("7th & Ruby", new LatLng(46.99869370025392,-120.54473608732224)));
        stops.add(new BusStop("2nd & Ruby", new LatLng(46.993479831089545,-120.54439544677734)));
        stops.add(new BusStop("Capitol & Ruby", new LatLng(46.9913649023684,-120.54430961608887)));
        stops.add(new BusStop("Ruby & Manitoba", new LatLng(46.98833333073611,-120.54407089948654)));

        cwBusRoute = new HashMap<>();
        cwBusRoute.put(BUS_WEEKEND_FLAG, 0);
        cwBusRoute.put(BUS_RUNNING_FLAG, 1);
        cwBusRoute.put(BUS_START_HOUR, 7);
        cwBusRoute.put(BUS_START_MIN, 0);
        cwBusRoute.put(BUS_END_HOUR, 18);
        cwBusRoute.put(BUS_END_MIN, 50);

        ccBusRoute = new HashMap<>();
        ccBusRoute.put(BUS_WEEKEND_FLAG, 1);
        ccBusRoute.put(BUS_RUNNING_FLAG, 1);
        ccBusRoute.put(BUS_START_HOUR, 7);
        ccBusRoute.put(BUS_START_MIN, 30);
        ccBusRoute.put(BUS_END_HOUR, 21);
        ccBusRoute.put(BUS_END_MIN, 15);
        ccBusRoute.put(BUS_WEEKEND_END_HOUR, 20);
        ccBusRoute.put(BUS_WEEKEND_END_MIN, 15);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        isSaturday = cal.get(Calendar.DAY_OF_WEEK) == 7;
        isSunday = cal.get(Calendar.DAY_OF_WEEK) == 1;
        isWeekend = isSaturday || isSunday;

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        float compTime = hour + (min/60);
        // logic for if is running

        if(isSaturday){
            busesActive = (compTime >= 8.5) && compTime <= 20.25;
        }else if(isSunday){
            busesActive = compTime>= 9.5 && compTime <= 20.25;
        }else{
            busesActive = compTime >= 7 && compTime <= 21.15;
        }
    }

    public boolean isBusesActive() {
        return busesActive;
    }

    public HashMap<String, Integer> getCcBusRoute() {
        if(isWeekend) {
            if(isSaturday){
                ccBusRoute.put(BUS_START_HOUR, ccBusRoute.get(BUS_START_HOUR) + 1); // 1 extra hour late on saturday
            }else{
                ccBusRoute.put(BUS_START_HOUR, ccBusRoute.get(BUS_START_HOUR) + 2); // 2 if sunday
            }
        }
        return this.ccBusRoute;
    }

    public HashMap<String, Integer> getCwBusRoute() {
        if(isWeekend) {
            cwBusRoute.put(BUS_RUNNING_FLAG, 0);
        }
        return cwBusRoute;
    }

    public boolean isSaturday() {
        return isSaturday;
    }

    public boolean isSunday() {
        return isSunday;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public ArrayList<LatLng> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<LatLng> route) {
        this.route = route;
    }

    public ArrayList<BusStop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<BusStop> stops) {
        this.stops = stops;
    }

    public HashMap<String, BusStop> getStopsMap(){
        HashMap<String, BusStop> stopsMap = new HashMap<String, BusStop>();
        for(BusStop stop : stops){
            stopsMap.put(stop.getName(),stop);
        }
        return stopsMap;
    }
}
