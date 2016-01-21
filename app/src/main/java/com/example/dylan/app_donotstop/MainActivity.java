package com.example.dylan.app_donotstop;

import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

    TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = getTabHost();
        TabView view = null;


        //speed view
        view = new TabView(this, R.drawable.icon_speed, R.drawable.icon_speed);
        TabHost.TabSpec speedSpec = mTabHost.newTabSpec("Speed");
        speedSpec.setIndicator(view);
        Intent speedIntent = new Intent(this,Speed.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        speedSpec.setContent(speedIntent);

        //input view
        view = new TabView(this, R.drawable.icon_input, R.drawable.icon_input);
        TabHost.TabSpec inputSpec = mTabHost.newTabSpec("Input");
        inputSpec.setIndicator(view);
        Intent inputIntent = new Intent(this,Input.class);
        inputSpec.setContent(inputIntent);

        //maps view
        view = new TabView(this, R.drawable.icon_map, R.drawable.icon_map);
        TabHost.TabSpec mapSpec = mTabHost.newTabSpec("Map");
        mapSpec.setIndicator(view);
        Intent mapIntent = new Intent(this,GoogleMaps.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mapSpec.setContent(mapIntent);

        //results view
        view = new TabView(this, R.drawable.icon_results, R.drawable.icon_results);
        TabHost.TabSpec resultSpec = mTabHost.newTabSpec("Results");
        resultSpec.setIndicator(view);
        Intent resultIntent = new Intent(this,Results.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultSpec.setContent(resultIntent);

        mTabHost.addTab(inputSpec);
        mTabHost.addTab(mapSpec);
        mTabHost.addTab(speedSpec);
        mTabHost.addTab(resultSpec);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
