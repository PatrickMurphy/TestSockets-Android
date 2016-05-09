package com.patrickmurphywebdesign.BusCentral.controller.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.patrickmurphywebdesign.BusCentral.controller.SocketManager;
import com.patrickmurphywebdesign.BusCentral.model.Bus;
import com.patrickmurphywebdesign.BusCentral.model.BusStop;
import com.patrickmurphywebdesign.BusCentral.controller.MapIcons;
import com.patrickmurphywebdesign.BusCentral.R;
import com.patrickmurphywebdesign.BusCentral.controller.RouteProperties;
import com.patrickmurphywebdesign.BusCentral.model.BusStopSchedule;

import java.util.ArrayList;
import java.util.HashMap;

public class BusCentralMap extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private HashMap<Integer, Marker> busCollectionMap;
    private HashMap<String, Marker> stopCollection;
    private RouteProperties routeProperties;
    private MapIcons mapIcons;
    private SocketManager mSocket;
    private int busUpdateCount;

    public void updateBusView(int busID, Bus BusModel){
        busUpdateCount++;

        if (!busCollectionMap.containsKey(busID)) {
            System.out.println("add bus " + busID);
            Marker tempMark = mMap.addMarker(BusModel.getMarker(mapIcons));
            busCollectionMap.put(busID, tempMark);
        } else {
            Marker thisBus = busCollectionMap.get(busID);
            thisBus.setVisible(true);

            if (!BusModel.getPosition().equals(thisBus.getPosition())) {
                thisBus.setPosition(BusModel.getPosition());
                thisBus.setIcon(mapIcons.getIcon_bus(BusModel.getHeading(), BusModel.getColor(), BusModel.getVelocity()));
            }
        }

        // if 5th recieved data then update stop times
        if (busUpdateCount % 5 == 0) {
            for (BusStop bs : routeProperties.getStops()) {
                BusStopSchedule tempBusStopSchedule = new BusStopSchedule(bs);
                String snippetText;

                if (bs.getIsTimed()) {
                    if (tempBusStopSchedule.hasNextTime()) {
                        snippetText = "Next stop: " + tempBusStopSchedule.getNextTime().getFormattedTime() + " Click for Full Schedule.";
                    } else {
                        snippetText = "";
                    }
                } else {
                    snippetText = "Click for details";
                }
                stopCollection.get(bs.getName()).setSnippet("Next stop: " + tempBusStopSchedule.getNextTime().getFormattedTime() + " Click for Full Schedule.");
            }
        }
    }

    public void setConnectionStatusIndicator(boolean connected, boolean init){
        if(connected) {
            ImageView iv = (ImageView) findViewById(R.id.SocketConnectedImage);
            iv.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.socket_connected, null));
            iv.setAlpha(new Float(0.2));

            TextView tv = (TextView) findViewById(R.id.SocketConnectedText);
            tv.setAlpha(new Float(0.12));
            tv.setText("Connected");

            Context context = getApplicationContext();
            CharSequence text = "Connected to BusCentral";

            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Context context = getApplicationContext();
            ImageView iv = (ImageView) findViewById(R.id.SocketConnectedImage);
            TextView tv = (TextView) findViewById(R.id.SocketConnectedText);

            iv.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.socket_not_connected, null));
            iv.setAlpha(new Float(0.55));

            tv.setAlpha(new Float(0.45));
            if(init){
                tv.setText("Connecting...");
            }else {
                tv.setText("Disconnected");

                CharSequence text = "Disconnected from BusCentral";

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void setConnectionStatusIndicator(boolean connected){
        this.setConnectionStatusIndicator(connected, false);
    }

    private void checkConnectivity(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean networkActive = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

        // if not connected to internet
        if(!networkActive){
            this.alertWindow("You are not connected to a internet network.");
        }

    }

    public void alertWindow(String Message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(Message);
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        /*builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
*/
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bus_central_map_overlay);

        routeProperties = new RouteProperties();
        mapIcons = new MapIcons(this);
        busCollectionMap = new HashMap<>();
        stopCollection = new HashMap<>();

        setConnectionStatusIndicator(false, true);
        mSocket = SocketManager.getInstance(this);

        if(routeProperties.isWeekend()){
            // alert about only one bus route and modified times
            //this.alertWindow("It is a weekend!");
        }

        // if no buses running
        if(!routeProperties.isBusesActive()){
            this.alertWindow("No buses are currently running.");
        }

        ImageButton aboutButton = (ImageButton) findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(BusCentralMap.this, About.class);
                    startActivity(intent);
                }catch (Exception e) {
                    System.out.println("MyActivity::MyMethod" + e.getMessage());
                }
            }
        });

        checkConnectivity();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        checkConnectivity();
        mSocket.reconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSocket.disconnect();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mSocket.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // setup map
        busUpdateCount = 0;
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);

        mSocket.connect();

        // add route
        mMap.addPolyline(new PolylineOptions().clickable(false).color(Color.argb((255 / 2), 17, 16, 16)).width(15).addAll(routeProperties.getRoute()));

        // add stops
        for(BusStop stop : routeProperties.getStops()){
            BusStopSchedule bss = new BusStopSchedule(stop);

            String snippetText;

            if(stop.getIsTimed()) {
                if (bss.hasNextTime()) {
                    snippetText = "Next stop: " + bss.getNextTime().getFormattedTime() + " Click for Full Schedule.";
                } else {
                    snippetText = "";
                }
            }else{
                snippetText = "Click for details";
            }
            Marker tempM = mMap.addMarker(new MarkerOptions().position(stop.getPosition()).title(stop.getName()).snippet(snippetText).draggable(false).icon(mapIcons.getIcon_stop_half()).anchor(Float.parseFloat("0.5"), Float.parseFloat("0.5")));

            if(stop.getIsTimed()){
                tempM.setIcon(mapIcons.getIcon_stop_threeFourths());
            }

            stopCollection.put(stop.getName(), tempM);
        }

        // add marker window click listener
        mMap.setOnInfoWindowClickListener(this);

        // center map on ellensburg
        LatLng ellensburg = new LatLng(46.99739759478534, -120.53604658203126);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ellensburg, 14));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(!routeProperties.getStopsMap().containsKey(marker.getTitle())){
            System.out.println("bus");
        }else{
            System.out.println(marker.getTitle());
        }
        try {
            Intent intent = new Intent(BusCentralMap.this, BusStopDetail.class);
            String message = marker.getTitle().toString();
            intent.putExtra("name", message);
            startActivity(intent);
        }catch (Exception e) {
            System.out.println("MyActivity::MyMethod" + e.getMessage());
        }
    }
}