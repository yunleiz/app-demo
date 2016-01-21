package com.example.dylan.app_donotstop;

/**
 * Created by Dylan on 15-03-16.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Identification {

    public static void run(){

        List<StopInfo> StopsFromdb = getStopsFromServer();
        List<RawCoordCalcs> StopsFromph = reorganizeStopsFromph();
        calculate(StopsFromdb,StopsFromph);
    }

    private static List<RawCoordCalcs> reorganizeStopsFromph(){
        return new ArrayList<RawCoordCalcs>();
    }

    private static List<StopInfo> getStopsFromServer(){
        return new ArrayList<StopInfo>();
    }

    public static void calculate(List<StopInfo> StopsFromdb, List<RawCoordCalcs> StopsFromph){

        /*//sort Stops from db by geoNode
        Collections.sort(StopsFromdb, new Comparator<StopInfo>(){
            public int compare(StopInfo a, StopInfo b){
                Integer ia = new Integer(a.geoNode);
                Integer ib = new Integer(b.geoNode);
                return ia.compareTo(ib);
            }
        });

        //sort Stop from ph by reading
        Collections.sort(StopsFromph, new Comparator<RawCoordCalcs>(){
            public int compare(RawCoordCalcs a, RawCoordCalcs b){
                Integer ia = new Integer(a.reading);
                Integer ib = new Integer(b.reading);
                return ia.compareTo(ib);
            }
        });*/

        List<PotentialStopInfo> potentialStops = new ArrayList<PotentialStopInfo>();

        int count = 1;

        // Extract the coordinates which have at least 12 entries (rows) in the raw data (i.e. determine potential stopping locations)
        for (int i = 1; i < StopsFromph.size(); i++){
            PotentialStopInfo potStop = new PotentialStopInfo();

            if ((StopsFromph.get(i).rawLat == StopsFromph.get(i-1).rawLat) && (StopsFromph.get(i).rawLong == StopsFromph.get(i-1).rawLong))
            {
                count++;
            }

            else if (count >= 11)
            {
                potStop.reading = StopsFromph.get(i-1).reading;
                potStop.lati = StopsFromph.get(i-1).rawLat;
                potStop.longi = StopsFromph.get(i-1).rawLong;
                potStop.numRows = count;
                potentialStops.add(potStop);
                count = 1;
            }
            else
            {
                count = 1;
            }
        }

        //sort potential stops by reading
        Collections.sort(potentialStops, new Comparator<PotentialStopInfo>(){
            public int compare(PotentialStopInfo a, PotentialStopInfo b){
                Integer ia = new Integer(a.reading);
                Integer ib = new Integer(b.reading);
                return ia.compareTo(ib);
            }
        });

        double latDiff = 0;
        double longDiff = 0;
        double totalDiff = 0;
        double minDiff = 1;

        for (int i = 0; i < StopsFromdb.size(); i++)
        {
            minDiff = 1;

            for (int j = 1; j < potentialStops.size(); j++)
            {
                latDiff = Math.abs(StopsFromdb.get(i).lati - potentialStops.get(j).lati);
                longDiff = Math.abs(StopsFromdb.get(i).longi - potentialStops.get(j).longi);
                totalDiff = latDiff + longDiff;

                if (minDiff > totalDiff)
                {
                    minDiff = totalDiff;
                    StopsFromdb.get(i).reading = potentialStops.get(j).reading;
                    StopsFromdb.get(i).dwellTime = potentialStops.get(j).numRows / 2;
                    StopsFromdb.get(i).latiEst = potentialStops.get(j).lati;
                    StopsFromdb.get(i).longiEst = potentialStops.get(j).longi;
                }
            }
        }
    }
}
