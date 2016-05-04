package com.patrickmurphywebdesign.BusCentral.model;

import com.google.android.gms.maps.model.LatLng;

public class BusStop {
    private String name;
    private LatLng position;
    private Boolean isTimed = false;
    private int ccOffset = 0;
    private int cwOffset = 0;

    // for not timed
    public BusStop(String name, LatLng position) {
        this.name = name;
        this.position = position;
    }

    // for timed
    public BusStop(String name, LatLng position, Boolean isTimed, int ccOffset, int cwOffset) {
        this.name = name;
        this.position = position;
        this.isTimed = isTimed;
        this.ccOffset = ccOffset;
        this.cwOffset = cwOffset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public Boolean getIsTimed() {
        return isTimed;
    }

    public void setIsTimed(Boolean isTimed) {
        this.isTimed = isTimed;
    }

    public int getCcOffset() {
        return ccOffset;
    }

    public void setCcOffset(int ccOffset) {
        this.ccOffset = ccOffset;
    }

    public int getCwOffset() {
        return cwOffset;
    }

    public void setCwOffset(int cwOffset) {
        this.cwOffset = cwOffset;
    }
}
