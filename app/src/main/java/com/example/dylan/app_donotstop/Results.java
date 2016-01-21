package com.example.dylan.app_donotstop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Results extends Activity {

    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8;
    Double totalinidwell = 0.0;
    Double totalactualdwell = 0.0;
    Double dif = 0.0;
    Double speed = 0.0;
    Double ons = 0.0;
    Double offs = 0.0;
    Double avg_est = 0.0;
    Double avg_act = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Singleton instance = Singleton.getInstance();
        totalinidwell = instance.getTotalinidwelltime();
        totalactualdwell = instance.getTotaldwelltime();
        dif = totalinidwell - totalactualdwell;
        speed = instance.getSpeed();
        ons = instance.getAvg_on();

        avg_act = instance.getAvg_act();
        avg_est = instance.getAvg_est();

        tx1 = (TextView)findViewById(R.id.estimated);
        tx2 = (TextView)findViewById(R.id.actual);

        tx4 = (TextView)findViewById(R.id.speed);
        tx5 = (TextView)findViewById(R.id.avgestimated);
        tx6 = (TextView)findViewById(R.id.avgactual);
        tx7 = (TextView)findViewById(R.id.on);

        ((Long)Math.round(totalinidwell) ).toString();

        tx1.setText(((Long)Math.round(totalinidwell) ).toString());
        tx2.setText(((Long)Math.round(totalactualdwell) ).toString());

        tx4.setText(((Long)Math.round(speed) ).toString());
        tx5.setText(((Long)Math.round(avg_est) ).toString());
        tx6.setText(((Long)Math.round(avg_act) ).toString());
        tx7.setText(((Long)Math.round(ons) ).toString());

    }

}
