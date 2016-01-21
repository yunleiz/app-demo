package com.example.dylan.app_donotstop;
import java.util.ArrayList;

/**
 * Created by Dylan on 15-03-16.
 */
public class Singleton {

    private static Singleton Instance;
    private ArrayList<Double> latitude = new ArrayList<Double>();
    private ArrayList<Double> longtitude = new ArrayList<Double>();
    private ArrayList<Double> initialboard = new ArrayList<Double>();
    private ArrayList<Double> initialalight = new ArrayList<Double>();
    private ArrayList<Double> initialdwelltime = new ArrayList<Double>();
    private double totaldwelltime;
    private double totalinidwelltime;
    private double speed;
    private double avg_est;
    private double avg_act;
    private double avg_on;
    private double avg_off;
    private ArrayList<Double> dwellTimeList = new ArrayList<Double>();


    private Singleton(){

        latitude = new ArrayList<Double>();
        longtitude = new ArrayList<Double>();
        initialboard = new ArrayList<Double>();
        initialalight = new ArrayList<Double>();
        initialdwelltime = new ArrayList<Double>();
        initialdwelltime = new ArrayList<Double>();
        avg_est = 0.0;
        avg_act = 0.0;
        avg_on = 0.0;
        avg_off= 0.0;
        totalinidwelltime = 0.0;
        totaldwelltime = 0.0;
        speed = 0.0;
    }


    public double getAvg_est() {
        return avg_est;
    }

    public void setAvg_est(double avg_est) {
        this.avg_est = avg_est;
    }

    public double getAvg_act() {
        return avg_act;
    }

    public void setAvg_act(double avg_act) {
        this.avg_act = avg_act;
    }

    public double getAvg_on() {
        return avg_on;
    }

    public void setAvg_on(double avg_on) {
        this.avg_on = avg_on;
    }

    public double getAvg_off() {
        return avg_off;
    }

    public void setAvg_off(double avg_off) {
        this.avg_off = avg_off;
    }

    public double getSpeed() {
       return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public static Singleton getInstance(){
        if (Instance == null){
            Instance = new Singleton();
        }

        return Instance;
    }


    public double getTotaldwelltime() {
        return totaldwelltime;
    }

    public void setTotaldwelltime(double totaldwelltime) {
        this.totaldwelltime = totaldwelltime;
    }

    public ArrayList<Double> getDwellTimeList() {
        return dwellTimeList;
    }

    public void setDwellTimeList(ArrayList<Double> dwellTimeList) {
        this.dwellTimeList = dwellTimeList;
    }

    public void setAll(ArrayList<Double> latitude,
                       ArrayList<Double> longtitude,
                       ArrayList<Double> initialboard,
                       ArrayList<Double> initialalight,
                       ArrayList<Double> initialdwelltime,
                       double totalinidwelltime){
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.initialboard = initialboard;
        this.initialalight = initialalight;
        this.initialdwelltime = initialdwelltime;
        this.totalinidwelltime = totalinidwelltime;

    }

    public double getTotalinidwelltime() {
        return totalinidwelltime;
    }

    public void setTotalinidwelltime(double totalinidwelltime) {
        this.totalinidwelltime = totalinidwelltime;
    }

    public ArrayList<Double> getLatitude() {
        return latitude;
    }

    public void setLatitude(ArrayList<Double> latitude) {
        this.latitude = latitude;
    }

    public ArrayList<Double> getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(ArrayList<Double> longtitude) {
        this.longtitude = longtitude;
    }

    public ArrayList<Double> getInitialboard() {
        return initialboard;
    }

    public void setInitialboard(ArrayList<Double> initialboard) {
        this.initialboard = initialboard;
    }

    public ArrayList<Double> getInitialalight() {
        return initialalight;
    }

    public void setInitialalight(ArrayList<Double> initialalight) {
        this.initialalight = initialalight;
    }

    public ArrayList<Double> getInitialdwelltime() {
        return initialdwelltime;
    }

    public void setInitialdwelltime(ArrayList<Double> initialdwelltime) {
        this.initialdwelltime = initialdwelltime;
    }
}
