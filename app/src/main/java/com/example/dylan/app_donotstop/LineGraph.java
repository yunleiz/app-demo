package com.example.dylan.app_donotstop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Dylan on 15-03-16.
 */
public class LineGraph {

    private GraphicalView view;

    private TimeSeries dataset = new TimeSeries("Speed");

    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

    private XYSeriesRenderer renderer = new XYSeriesRenderer();

    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

    public LineGraph(){

        mDataset.addSeries(dataset);
        mRenderer.setYAxisMin(0.0);
        mRenderer.setYAxisMax(15.0);
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(30);
        mRenderer.setAxesColor(Color.WHITE);
        mRenderer.setShowGrid(true);
        mRenderer.setGridColor(Color.parseColor("#ff008000"));

        //every half a second retrieve a data
        mRenderer.setXLabels(60);
        mRenderer.setYLabels(30);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setPointSize((float)2);
        mRenderer.setShowLegend(false);

        renderer.setColor(Color.GREEN);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setLineWidth((float)8);
        renderer.setFillPoints(true);

        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setXTitle("Time");
        mRenderer.setYTitle("m/s in Speed");

        mRenderer.addSeriesRenderer(renderer);

    }


    public GraphicalView getView(Context context){

        view = ChartFactory.getLineChartView(context, mDataset, mRenderer);

        return view;

    }

    public void addNewPoints(Point p){

        dataset.add(p.getX(),p.getY());

    }
}
