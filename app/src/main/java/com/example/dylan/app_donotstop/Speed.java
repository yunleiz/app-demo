package com.example.dylan.app_donotstop;

import android.app.ActionBar;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.achartengine.GraphicalView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Speed extends Activity {

    TextView text;
    private Queue<Double> __lng = new LinkedList<Double>();
    private Queue<Double> __lag = new LinkedList<Double>();
    private Queue<Double> __spd = new LinkedList<Double>(); //This will be showed in the chart wrt time
    private List<Double> lng = new ArrayList<Double>();
    private List<Double> lag = new ArrayList<Double>();
    private List<Double> spd = new ArrayList<Double>();
    private Timer timer;
    private TimerTask recorder;
    private boolean recorderStart;
    private boolean showGraph = true;

    private static GraphicalView view;
    private LineGraph line = new LineGraph();
    private static Thread thread;

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        // bar = getActionBar();
        // bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        class RecorderTask extends TimerTask{
            public void run() {
                recorderStart = true;
                if (__lag.isEmpty() && lag.isEmpty())
                    return;
                if (__lag.isEmpty()) {
                    Double temp;
                    temp = lag.get(lag.size() - 1);
                    lag.add(temp);
                    temp = lng.get(lng.size() - 1);
                    lng.add(temp);
                    temp = 0.0;
                    spd.add(temp);
                    return;
                }
                lag.add(__lag.peek());
                lng.add(__lng.peek());
                spd.add(__spd.peek());
                __lag.remove();
                __lng.remove();
                __spd.remove();
            }
        }


        text = (TextView) findViewById(R.id.info);
        recorderStart = false;
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationListener();
        lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, ll);
        Button StoreGPS = (Button) findViewById(R.id.StoreGPS);

        StoreGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer != null) {
                    recorderStart = false;
                    timer.cancel();
                    timer.purge();
                    timer = null;
                    recorder.cancel();
                }

                LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
                layout.removeAllViews();

                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/__GPSInfo.txt");
                File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/GPSInfo.txt");
                showGraph = false;
                try {
                    FileOutputStream FOs = new FileOutputStream(file);
                    byte[] bytes = lng.toString().getBytes();
                    FOs.write(bytes);
                    FOs.flush();
                    FOs.close();
                } catch (Exception e) {
                }
                try {
                    FileOutputStream FOs = new FileOutputStream(file1);
                    byte[] bytes = lag.toString().getBytes();
                    FOs.write(bytes);
                    FOs.flush();
                    FOs.close();
                } catch (Exception e) {
                }
            }
        });

        final Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getApplicationContext(),Speed.class);
//                startActivity(intent);
                showGraph = true;
                if (text.getText().length() != 0) {

                    if (timer == null) {
                        if (!__lag.isEmpty()) {
                            __lag.clear();
                            __lng.clear();
                            __spd.clear();
                        }
                        timer = new Timer();
                        recorder = new RecorderTask();
                        timer.purge();
                        timer.schedule(recorder, 0, 500);

                    }
                    else {
                        if (!__lag.isEmpty()) {
                            __lag.clear();
                            __lng.clear();
                            __spd.clear();
                        }
                        timer.cancel();
                        recorder.cancel();
                        timer.purge();
                        timer =new Timer();
                        recorder = new RecorderTask();
                        recorderStart = false;
                        timer.schedule(recorder,0,500);
                    }
                    line = new LineGraph();
                    view = line.getView(getApplicationContext());
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
                    layout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

                    thread = new Thread() {

                        public void run() {

                            double i = 0.0;

                            while (showGraph) {

                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // Point p = MockData.getDataFromReceiver(i, spd);
                                Point p = new Point(i, Double.parseDouble(text.getText().toString()));
                                i = i + 0.5;
                                line.addNewPoints(p);
                                view.repaint();

                            }
                        }

                    };
                    thread.start();

                }else{
                    Toast.makeText(getApplicationContext(), "please wait for gps to track speed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class mylocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                __lng.offer(location.getLongitude());
                __lag.offer(location.getLatitude());
                __spd.offer((double) location.getSpeed());
                if (location.hasSpeed()) {
                    text.setText(Float.toString(location.getSpeed()));
                } else {
                    text.setText(0 + "");
                }
            }
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
}
