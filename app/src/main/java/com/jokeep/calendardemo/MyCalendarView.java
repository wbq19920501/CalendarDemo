package com.jokeep.calendardemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wbq501 on 2016-2-19 10:13.
 * CalendarDemo
 */
public class MyCalendarView extends LinearLayout implements View.OnClickListener{
    public MyCalendarView(Context context) {
        super(context);
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {

    }
}
