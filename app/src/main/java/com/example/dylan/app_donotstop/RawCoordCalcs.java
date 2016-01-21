package com.example.dylan.app_donotstop;

/**
 * Created by Dylan on 15-03-16.
 */
public class RawCoordCalcs {

    public int reading;
    public double rawLat;
    public double rawLong;


    public RawCoordCalcs()
    {
        reading = 0;
        rawLat = 0;
        rawLong = 0;
    }

    public RawCoordCalcs(int x, double y, double z){
        reading = x;
        rawLat = y;
        rawLong = z;
    }

}