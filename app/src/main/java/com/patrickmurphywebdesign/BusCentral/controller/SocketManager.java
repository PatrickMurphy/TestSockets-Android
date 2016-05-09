package com.patrickmurphywebdesign.BusCentral.controller;

import android.content.Context;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.patrickmurphywebdesign.BusCentral.controller.activity.BusCentralMap;
import com.patrickmurphywebdesign.BusCentral.model.Bus;
import com.patrickmurphywebdesign.BusCentral.model.BusStop;
import com.patrickmurphywebdesign.BusCentral.model.BusStopSchedule;

import java.net.URISyntaxException;
import android.os.Handler;

import org.json.JSONObject;

public class SocketManager {

    private static Context context;
    private static Handler handler;
    private static BusCentralMap activity;
    private static final String SERVER_URL = "http://buscentral.herokuapp.com/";

    protected SocketManager(){
        // empty constuctor
    }

    private static class SingletonHolder {
        private static final SocketManager INSTANCE = new SocketManager();
    }

    public static SocketManager getInstance(BusCentralMap act){
        context = act.getApplicationContext();
        activity = act;
        handler = new Handler(context.getMainLooper());
        return SingletonHolder.INSTANCE;
    }

    private static void runOnUiThread(Runnable r) {
        handler.post(r);
    }

    private static Emitter.Listener onConnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
                System.out.println("----- Connected -----");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.setConnectionStatusIndicator(true);
                    }
                });
        }
    };

    private static Emitter.Listener onDisconnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("----- Disconnected -----");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.setConnectionStatusIndicator(false);
                }
            });
        }
    };

    private static Emitter.Listener onReconnectAttempt = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("attempting to reconnect to socket io");
        }
    };

    private static Emitter.Listener onBusLocationEvent = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MapIcons MapIcons = new MapIcons(activity);
                        JSONObject data = (JSONObject) args[0];
                        // for each bus
                        for (int i = 0; i < data.length(); i++) {
                            // get new position etc
                            int busID = data.names().getInt(i);
                            JSONObject busJson = (JSONObject) data.get(data.names().get(i).toString());
                            Bus BusModel = new Bus(busID, i, busJson);

                            activity.updateBusView(busID, BusModel);
                        }
                    } catch (Exception e) {
                        //    return;
                        System.out.println("error");
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    public static void addHandlers(){
        mSocket.on(Socket.EVENT_CONNECT, onConnected)
                .on(Socket.EVENT_RECONNECT, onConnected)
                .on(Manager.EVENT_RECONNECT_ATTEMPT, onReconnectAttempt)
                .on(Socket.EVENT_DISCONNECT, onDisconnected);
        mSocket.on("buses-location", onBusLocationEvent);
    }

    public static void removeHandlers(){
        mSocket.off(Socket.EVENT_CONNECT);
        mSocket.off(Socket.EVENT_RECONNECT_ATTEMPT);
        mSocket.off(Socket.EVENT_RECONNECT);
        mSocket.off(Socket.EVENT_DISCONNECT);

        mSocket.off("buses-location");
    }

    public static void connect(){
        addHandlers();
        mSocket.connect();
    }

    public static void reconnect(){
        if(!mSocket.connected()) {
            removeHandlers();
            addHandlers();
            mSocket.connect();
        }
    }

    public static void disconnect(){
        removeHandlers();
        mSocket.disconnect();
    }

    private static Socket mSocket;
    {
        try {
            mSocket = IO.socket(SERVER_URL);

            connect();

        } catch (URISyntaxException e) {
            System.out.println("URI SYNTAX EXCEPTION");
        }
    }

}
