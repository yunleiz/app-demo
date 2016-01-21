package com.example.dylan.app_donotstop;

/**
 * Created by Dylan on 15-03-16.
 */
public class StopInfo {

    public int geoNode;
    public int reading;
    public double lati;
    public double longi;
    public double latiEst;
    public double longiEst;
    public double dwellTime;

    public StopInfo()
    {
        geoNode = 0;
        reading = 0;
        longi = 0;
        lati = 0;
        dwellTime = 0;
        longiEst = 0;
        latiEst = 0;
    }
}

