package com.example.dylan.app_donotstop;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Dylan on 15-03-16.
 */
public class GoogleMaps extends Activity implements LocationListener {

    private GoogleMap mMap;
    LocationManager locationManager;

    private ArrayList<Double> lati = new ArrayList<Double>();
    private ArrayList<Double> logi = new ArrayList<Double>();

    private ArrayList<Double> dwell = new ArrayList<Double>();
    private ArrayList<Double> dwell1 = new ArrayList<Double>();

    private ArrayList<Double> board = new ArrayList<Double>();
    private ArrayList<Double> alight = new ArrayList<Double>();


    private Double[] lati_ = {43.656534,43.658105,43.657685,43.659707,43.660989,43.663456,43.66527,43.667594,43.669215,43.671168,43.672602,43.674596,43.677082,43.679437,43.682919,43.684707,43.686901,43.688587,43.690876,43.691546,43.692706,43.693635,43.695385,43.698336,43.699891,43.701716,43.703125,43.705223,43.705455,43.705715,43.706244,43.705583};
    private Double[] logi_ = {-79.387915,-79.386763,-79.388907,-79.390261,-79.390282,-79.390547,-79.391292,-79.393655999999993,-79.39435,-79.395146,-79.395713,-79.396527,-79.397533,-79.3985,-79.399904,-79.400622,-79.401587,-79.402245,-79.402307,-79.400951,-79.401452,-79.401809,-79.402369,-79.403406,-79.403939,-79.404662,-79.405276,-79.405563,-79.404495,-79.403233,-79.400687,-79.399687};
    private int[] board_ = {3,2,8,4,7,2,3,6,5,2,1,3,3,1,1,2,5,2,1,3,1,1,2,1,4,2,3,3,5,1,1,0};
    private int[] alight_ = {0,1,1,1,1,2,1,4,4,2,3,4,4,2,1,1,4,1,1,2,1,1,2,2,1,3,4,5,3,5,2,19};
    private Double[] ini_dwell = {14.400000000000002,11.600000000000001,28.400000000000002,17.200000000000003,25.6,11.600000000000001,14.400000000000002,22.800000000000004,20.0,11.600000000000001,10.11,14.400000000000002,14.400000000000002,8.94,8.8,11.600000000000001,20.0,11.600000000000001,8.8,14.400000000000002,8.8,8.8,11.600000000000001,8.94,17.200000000000003,11.600000000000001,14.400000000000002,14.400000000000002,20.0,12.45,8.94,28.229999999999997};

    private Double[] ini_dwell1 = {14.4,11.6,28.4,17.2,25.6,11.6,14.4,22.8,20.0,11.6,10.11,14.4,14.4,8.94,8.8,11.6,20.0,11.6,8.8,14.4,8.8,8.8,11.6,8.94,17.2,11.600000000000001,14.400000000000002,14.400000000000002,20.0,12.45,8.94,28.229999999999997};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map))
                .getMap();


        mMap.setMyLocationEnabled(true);

            //initiateArrayList();
            Singleton instance = Singleton.getInstance();
            lati = instance.getLatitude();
            logi = instance.getLongtitude();
            board = instance.getInitialboard();
            alight = instance.getInitialalight();
            dwell = instance.getInitialdwelltime();

            for(int i = 0; i < lati.size();i++){
               dwell1.add(0.0);
           }
            dwell1 = instance.getDwellTimeList();

       /*lati = (ArrayList<Double>) getIntent().getSerializableExtra("File_to_Send_1");
       logi = (ArrayList<Double>) getIntent().getSerializableExtra("File_to_Send_2");
         board= (ArrayList<Double>) getIntent().getSerializableExtra("File_to_Send_3");
        alight = (ArrayList<Double>) getIntent().getSerializableExtra("File_to_Send_4");
        dwell = (ArrayList<Double>) getIntent().getSerializableExtra("File_to_Send_5");*/
        if(lati.size() != 0) {

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });

            drawMarker_multi();
        }
    }

    private void drawMarker_multi() {

        mMap.clear();

        int size = lati.size();
        for (int i = 0 ;i< size ; i++){

            LatLng currentPosition = new LatLng(lati.get(i),logi.get(i));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati.get(size / 2), logi.get(size / 2)), 12));

            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,11));

//            mMap.addMarker(new MarkerOptions().position(currentPosition).title("Stop" + (i+1)).snippet("latitude:" + lati.get(i) + "\nlongitude: " +
//                    logi.get(i)));

            mMap.addMarker(new MarkerOptions().position(currentPosition).title("Stop" + (i+1)).snippet("Ons:" + Math.round(board.get(i)) + "   Offs:" +
                    Math.round(alight.get(i)) + "   Est Dwell:" + Math.round(dwell.get(i)) + "  Actual Dwell:" + Math.round(dwell1.get(i))));

        }

    }

    private void initiateArrayList() {

        for (int i = 0; i < lati_.length ; i++) {

            lati.add(lati_[i]);
        }

        for (int i = 0 ; i< logi_.length; i++) {

            logi.add(logi_[i]);
        }

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

