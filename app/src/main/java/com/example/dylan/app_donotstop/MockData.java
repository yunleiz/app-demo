package com.example.dylan.app_donotstop;

import java.util.List;

/**
 * Created by Dylan on 15-03-16.
 */
public class MockData {

    public static Point getDataFromReceiver(double x, List<Double> spd){

        if(!spd.isEmpty()) {

            return new Point(x, spd.get((int) (2*x)));
        }

        else{

            return new Point(x,0.0);
        }

    }
}

