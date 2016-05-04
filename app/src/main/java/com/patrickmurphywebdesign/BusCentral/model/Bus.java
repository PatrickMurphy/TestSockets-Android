package com.patrickmurphywebdesign.BusCentral.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.patrickmurphywebdesign.BusCentral.controller.MapIcons;

import org.json.JSONException;
import org.json.JSONObject;

public class Bus {
    private int id;
    private LatLng position;
    private String name;
    private int velocity;
    private int color;
    private int heading;
    private int timeAt;
    private String timestamp;

    public Bus(int id, LatLng position, String name, int color, int velocity, int heading, int timeAt, String timestamp) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.color = color;
        this.velocity = velocity;
        this.heading = heading;
        this.timeAt = timeAt;
        this.timestamp = timestamp;
    }

    public Bus(int id, int color, JSONObject busJson) {
        JSONObject coordinates = null;
        try {
            this.id = id;
            coordinates = (JSONObject) busJson.get("coordinates");
            this.position = new LatLng(coordinates.getDouble("lat"), coordinates.getDouble("lng"));
            this.name = busJson.get("name").toString();
            this.color = color;
            this.velocity = busJson.getInt("velocity");
            this.heading = busJson.getInt("heading");
            this.timeAt = busJson.getInt("timeAt");
            this.timestamp = busJson.getString("timestamp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MarkerOptions getMarker(MapIcons mapIcons){
        return new MarkerOptions().flat(true).position(position).title(name).anchor(Float.parseFloat("0.5"), Float.parseFloat("0.5")).icon(mapIcons.getIcon_bus(heading, color, velocity));
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(int timeAt) {
        this.timeAt = timeAt;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
