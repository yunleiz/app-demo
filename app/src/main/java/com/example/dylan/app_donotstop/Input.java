package com.example.dylan.app_donotstop;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.InputStream;


public class Input extends Activity implements View.OnClickListener,AdapterView.OnItemSelectedListener,Serializable {

    DateFormat format = DateFormat.getDateInstance();
    Calendar calendar = Calendar.getInstance();

    DateFormat format1 = DateFormat.getDateInstance();
    Calendar calendar1 = Calendar.getInstance();


    TextView label,label1;
    Button btn, btn1,btn2;
    Spinner daySpinner,timeSpinner,routeSpiner,patternSpiner;
    String single_pattern = "1234567";
    private String value = "";

    ArrayList<Double> latitude = new ArrayList<Double>();
    ArrayList<Double> longitude = new ArrayList<Double>();
    ArrayList<Double> initialdwelltime = new ArrayList<Double>();
    ArrayList<Double> initialboard = new ArrayList<Double>();
    ArrayList<Double> initialalight = new ArrayList<Double>();
    private double totalinidwelltime = 0.0;
    private double routelength = 0.0;
    private double speed = 0.0;
    private double avg_est = 0.0;
    private double avg_act = 0.0;
    private double avg_on = 0.0;
    private double avg_off = 0.0;
    private int flag1 = 0;
    private int flag2 = 0;

    private String result1 = null;
    private String result2 = null;
    private String result3 = null;
    private String result4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        label = (TextView)findViewById(R.id.calendar);
        label1 = (TextView)findViewById(R.id.calendar1);

        btn = (Button)findViewById(R.id.checkResults);
        btn2 = (Button)findViewById(R.id.checkResults1);


        btn1 = (Button)findViewById(R.id.sendResults);
        daySpinner = (Spinner)findViewById(R.id.dayweek);
        timeSpinner =(Spinner)findViewById(R.id.time);
        routeSpiner = (Spinner)findViewById(R.id.route);
        patternSpiner = (Spinner)findViewById(R.id.routeP);

        label.setTextColor(Color.parseColor("#ff008000"));
        label1.setTextColor(Color.parseColor("#ff008000"));

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter1);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter2);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.route,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpiner.setAdapter(adapter3);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ff008000"));
                ((TextView) parent.getChildAt(0)).setTextSize(22);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ff008000"));
                ((TextView) parent.getChildAt(0)).setTextSize(22);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        routeSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // parent  ==  route
                Spinner spinner = (Spinner)parent;
                String pro = (String)spinner.getItemAtPosition(position);

                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ff008000"));
                ((TextView) parent.getChildAt(0)).setTextSize(22);

                ArrayAdapter adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.patternDefault,android.R.layout.simple_spinner_item);

               // patternSpiner.setAdapter(adapter4);

                if(pro.equals("1")){

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern1,android.R.layout.simple_spinner_item);

                }else if(pro.equals("3")){

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern3,android.R.layout.simple_spinner_item);

                }else if(pro.equals("4")){

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern4,android.R.layout.simple_spinner_item);

                }else if(pro.equals("5")){

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern5,android.R.layout.simple_spinner_item);

                }else if(pro.equals("6")){

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern6,android.R.layout.simple_spinner_item);

                }else{

                    adapter4 = ArrayAdapter.createFromResource(Input.this,R.array.pattern555,android.R.layout.simple_spinner_item);
                }

                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                patternSpiner.setAdapter(adapter4);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        patternSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                single_pattern = ((TextView) parent.getChildAt(0)).getText().toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#ff008000"));
                ((TextView) parent.getChildAt(0)).setTextSize(22);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        updateDate();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate1();
            }
        });
        updataDate1();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                latitude.clear();
                longitude.clear();
                initialalight.clear();
                initialboard.clear();
                initialdwelltime.clear();
                totalinidwelltime = 0.0;

                //overwrite single_pattern
                //single_pattern = "1234567";
                //read result information

                //make sure we still are getting new inforomation and then go to the next stage to store the information

                if (single_pattern != ""){
                    Thread thread = new Thread(myRun);
                    thread.start();
                    while(value.equals(""));
                    while(flag1==flag2);
                    flag1++;
                }

                String la1 = value.substring(value.indexOf("stopLat")+"stopLat".length()+2,value.indexOf("stopLong")-2);

                int number1 = la1.indexOf("[");
                int number2 = la1.lastIndexOf("]");
                String la = la1.substring(number1 + 1, number2);

                String ln = value.substring(value.indexOf("stopLong") + "stopLong".length() + 3, value.indexOf("initialBoard")-3);
                String[] ss = la.split(",");

                String idw = value.substring(value.indexOf("initialDwell") + "initialDwell".length() + 3, value.indexOf("totalInitialDwellEstimate")-3);
                String[] idw_ = idw.split(",");

                String ion = value.substring(value.indexOf("initialBoard") + "initialBoard".length() + 3, value.indexOf("initialAlight")-3);
                String[] ion_ = ion.split(",");

                String ioff = value.substring(value.indexOf("initialAlight") + "initialAlight".length() + 3, value.indexOf("initialDwell")-3);
                String[] ioff_ = ioff.split(",");

                String time111 = value.substring(value.indexOf("totalInitialDwellEstimate")+"totalInitialDwellEstimate".length()+2,value.indexOf("}"));

                totalinidwelltime = Double.parseDouble(time111);

                String length = value.substring(value.indexOf("totalPatternLength")+"totalPatternLength".length()+2,value.indexOf("stopLat")-2);

                routelength = Double.parseDouble(length);

                for (String single_ss : ss){
                    latitude.add(Double.parseDouble(single_ss));
                }

                String[] sp = ln.split(",");

                for (String single_sp : sp){
                    longitude.add(Double.parseDouble(single_sp));
                }

                for(String single_idw : idw_){

                    initialdwelltime.add(Double.parseDouble(single_idw));

                }

                for(String single_ion: ion_){
                    initialboard.add(Double.parseDouble(single_ion));

                }

                for(String single_ioff: ioff_){
                    initialalight.add(Double.parseDouble(single_ioff));
                }

                //Toast.makeText(getApplicationContext(),latitude.size() + "", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),totalinidwelltime+"", Toast.LENGTH_LONG).show();

                // get stops from server

                    List<StopInfo> stopsFromServer = new ArrayList<StopInfo>();
                    for (int i = 0; i < latitude.size(); i++) {
                        StopInfo si = new StopInfo();
                        si.lati = latitude.get(i);
                        si.longi = longitude.get(i);
                        si.dwellTime = initialdwelltime.get(i);
                        stopsFromServer.add(si);
                    }

                    // read recording stops from file
                    List<RawCoordCalcs> stopsFromph = new ArrayList<RawCoordCalcs>();
                    InputStream is = getResources().openRawResource(R.raw.latitude);
                    InputStreamReader inputStreamReader = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(inputStreamReader);
                    StringBuffer sb = new StringBuffer("");
                    String line;
                    try {
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                    }
                    String[] latitudes = sb.toString().split(",");
                    InputStream is1 = getResources().openRawResource(R.raw.longitude);
                    InputStreamReader inputStreamReader1 = new InputStreamReader(is1);
                    BufferedReader br1 = new BufferedReader(inputStreamReader1);
                    StringBuffer sb1 = new StringBuffer("");
                    String line1;
                    try {
                        while ((line1 = br1.readLine()) != null) {
                            sb1.append(line1);
                        }
                    } catch (IOException e) {
                    }
                    String[] longitudes = sb1.toString().split(",");
                    for (int i = 0; i < latitudes.length; i++) {
                        RawCoordCalcs rc = new RawCoordCalcs(i,
                                Double.parseDouble(latitudes[i]),
                                Double.parseDouble(longitudes[i]));
                        stopsFromph.add(rc);
                    }

                    // calculate speed
                    speed = routelength * 2 * 3.6 / longitudes.length;

                    //calculate avg est dwell time
                    avg_est = totalinidwelltime / longitude.size();

                    Double sum1 = 0.0;
                    for(Double single_on: initialboard){
                        sum1 = sum1 + single_on;
                    }
                    avg_on = sum1/ longitude.size();

                    Double sum2 = 0.0;
                    for(Double single_off : initialalight){
                        sum2 = sum2 + single_off;
                    }
                    avg_off = sum2/longitude.size();

                    //calculate actual dwell time, the result is reside in stopsFromServer.
                    Identification.calculate(stopsFromServer, stopsFromph);

                    //abstract actual dwell time from stopsFromServer
                    //return me the  total dwell time and actual dwell time for each stop
                    ArrayList<Double> dwellTimeList = new ArrayList<Double>();
                    double totalDwellTime = 0;
                    for (int i = 0; i < stopsFromServer.size(); i++) {
                        double time = stopsFromServer.get(i).dwellTime;
                        totalDwellTime += time;
                        dwellTimeList.add(time);
                    }

                    if(!single_pattern.equals("1234567")){
                        totalDwellTime = totalinidwelltime;
                    }

                    avg_act = totalDwellTime / longitude.size();


//
//                    Singleton instance = Singleton.getInstance();
//                    instance.setDwellTimeList(dwellTimeList);
//                    instance.setTotaldwelltime(totalDwellTime);
//                    instance.setSpeed(speed);


                //Toast.makeText(getApplicationContext(),single_pattern +"h!!!" + longitude.size(), Toast.LENGTH_LONG).show();
                Singleton instance = Singleton.getInstance();
                instance.setAll(latitude,longitude,initialboard,initialalight,initialdwelltime,totalinidwelltime);

                instance.setDwellTimeList(dwellTimeList);
                instance.setTotaldwelltime(totalDwellTime);
                instance.setSpeed(speed);
                instance.setAvg_est(avg_est);
                instance.setAvg_act(avg_act);
                instance.setAvg_on(avg_on);
                instance.setAvg_off(avg_off);

                Intent intent = new Intent(getApplicationContext(),GoogleMaps.class);
                intent.putExtra("File_to_Send_1",latitude);
                intent.putExtra("File_to_Send_2",longitude);
                intent.putExtra("File_to_Send_3",initialboard);
                intent.putExtra("File_to_Send_4",initialalight);
                intent.putExtra("File_to_Send_5",initialdwelltime);
                startActivity(intent);

            }
        });
    }


    Runnable myRun = new Runnable() {

        URL url;
        @Override
        public void run() {

            String urlPrefix = "http://67.71.41.41/home/getResults?yearList=%272013%27,%272012%27,%272011%27,%272010%27,%272009%27,%272008%27&monthList=%271%27,%272%27,%273%27,%274%27,%275%27,%276%27,%277%27,%278%27,%279%27,%2710%27,%2711%27,%2712%27&dayList=%271%27,%272%27,%273%27,%274%27,%275%27,%276%27,%277%27,%278%27,%279%27,%2710%27,%2711%27,%2712%27,%2713%27,%2714%27,%2715%27,%2716%27,%2717%27,%2718%27,%2719%27,%2720%27,%2721%27,%2722%27,%2723%27,%2724%27,%2725%27,%2726%27,%2727%27,%2728%27,%2729%27,%2730%27,%2731%27&weekList=%27Sun%27,%27Mon%27,%27Tues%27,%27Wed%27,%27Thu%27,%27Fri%27,%27Sat%27&scheduleList=%27am%20peak%27,%27Base%27,%27Early%20am%27,%27Early%20Eve%27,%27evening%27,%27last%20trips%27,%27late%20Evening%27,%27Mid%20Day%27,%27Peak%27,%27pm%20Peak%27,%27Sunday%20and%20holidays%27&patternId=";
            String urlSuffix = "&method=delete";
            String search_url_string = urlPrefix + single_pattern + urlSuffix;

            try {
                url = new URL(search_url_string);
            }catch (Exception e){}

            try {
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setConnectTimeout(300000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Connection", "keep-alive");
                //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
                //conn.setRequestProperty("Accept", "*/*");
                //conn.setRequestProperty("Referer", "https://www.google.ca/#q=microsoft");
                conn.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String str;
                while((str = br.readLine())!= null){
                    sb.append(str + "\n");
                }
                br.close();
                value = sb.toString();
                conn.disconnect();
                flag2 ++;
                //Toast.makeText(getApplicationContext(),single_pattern + "", Toast.LENGTH_LONG).show();
            }catch (Exception e){System.out.print("ee:" + e.getMessage());}
        }
    };

    public void updateDate(){

        label.setText(format.format(calendar.getTime()));
    }

    public void updataDate1(){

        label1.setText(format1.format(calendar.getTime()));
    }


    public void setDate(){

        new DatePickerDialog(Input.this,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void setDate1(){

        new DatePickerDialog(Input.this,d1,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateDate();

        }
    };

    DatePickerDialog.OnDateSetListener d1 = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updataDate1();

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}