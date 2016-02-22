package com.jokeep.calendardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;


import com.jokeep.calendardemo.manager.CalendarManager;
import com.jokeep.calendardemo.view.MyScrollView;
import com.jokeep.calendardemo.view.WeekView;

import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wbq501 on 2016-2-18 17:52.
 * CalendarDemo
 */
public class RiLiActivity extends Activity implements MyScrollView.OnScrollListener{
    /**
     * 自定义的MyScrollView
     */
    private MyScrollView myScrollView;
    /**
     * 位于顶部的购买布局
     */
    private LinearLayout mTopBuyLayout;
    CollapseCalendarView calendar;
    private CalendarManager mManager;
    private JSONObject json;
    private SimpleDateFormat sdf;
    WeekView top_buy_layout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rili);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        calendar = (CollapseCalendarView) findViewById(R.id.calendar);
        mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);
        top_buy_layout2 = (WeekView) findViewById(R.id.top_buy_layout2);
        init();
        myScrollView.setOnScrollListener(this);

        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
            }
        });
    }

    private void init() {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        mManager = new CalendarManager(LocalDate.now(),
                CalendarManager.State.MONTH, LocalDate.now().withYear(100),
                LocalDate.now().plusYears(100));
        //月份切换监听器
        mManager.setMonthChangeListener(new CalendarManager.OnMonthChangeListener() {

            @Override
            public void monthChange(String month, LocalDate mSelected) {
            }

        });
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        json = new JSONObject();
        try {
            for (int i = 0; i < 30; i++) {
                JSONObject jsonObject2 = new JSONObject();
                if (i <= 6) {
                    jsonObject2.put("type", "休");
                } else if ( i > 6 && i< 11) {
                    jsonObject2.put("type", "班");
                }
                if (i%3 == 0) {
                    jsonObject2.put("list", new JSONArray());
                }

                json.put(sdf.format(cal.getTime()), jsonObject2);

                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //设置数据显示
        calendar.setArrayData(json);
        //初始化日历管理器
        calendar.init(mManager);
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, calendar.getTop()+20);
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }
}
