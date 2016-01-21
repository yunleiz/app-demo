package com.example.dylan.app_donotstop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Dylan on 15-03-16.
 */
public class TabView extends LinearLayout {

    ImageView imageView;

    public TabView(Context c, int drawable, int drawableselec) {
        super(c);
        imageView = new ImageView(c);
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(SELECTED_STATE_SET, this.getResources()
                .getDrawable(drawableselec));
        listDrawable.addState(ENABLED_STATE_SET, this.getResources()
                .getDrawable(drawable));
        imageView.setImageDrawable(listDrawable);
        imageView.setBackgroundColor(Color.TRANSPARENT);
        setGravity(Gravity.CENTER);
        addView(imageView);
    }
}

