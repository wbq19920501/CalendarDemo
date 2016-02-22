package com.jokeep.calendardemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.jokeep.calendardemo.R;
import com.jokeep.calendardemo.manager.CalendarManager;
import com.jokeep.calendardemo.manager.ResizeManager;
import com.jokeep.calendardemo.manager.ResizeManager2;

import org.joda.time.LocalDate;

/**
 * Created by wbq501 on 2016-2-19 15:32.
 * CalendarDemo
 */
public class MyCollaspCalendarView extends ScrollView implements View.OnClickListener{

    public OnScrollListener onScrollListener;

    private String[] weeks;
    private LayoutInflater mInflater;
    private ResizeManager2 mResizeManager;
    private CalendarManager manager;
    private LinearLayout weeksView;
    private Animation left_in;
    private Animation right_in;

    private CalendarManager mManager;
    private OnDateSelect mListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public MyCollaspCalendarView(Context context) {
        this(context, null);
    }
    public MyCollaspCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyCollaspCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        weeks = getResources().getStringArray(R.array.weeks);
        mInflater = LayoutInflater.from(context);
        mResizeManager = new ResizeManager2(this);
        inflate(context, R.layout.mycalendar, this);
        setBackgroundColor(getResources().getColor(R.color.cal_color_white));
        initAnim();
    }
    /**
     * 初始化左右滑动动画
     */
    private void initAnim() {
        left_in = AnimationUtils.makeInAnimation(getContext(), true);
        right_in = AnimationUtils.makeInAnimation(getContext(), false);
    }
    /**
     * 初始化日历管理器
     * @param manager 日历管理器对象
     */
    public void init(CalendarManager manager) {
        if (manager != null) {
            mManager = manager;
            if (mListener != null) {
                mListener.onDateSelected(mManager.getSelectedDay());
            }
            populateLayout();
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 上一页
     */
    public void prev() {

    }

    /**
     * 下一页
     */
    public void next() {

    }

    public CalendarManager getManager() {
        return manager;
    }

    public void populateLayout() {
    }

    public LinearLayout getWeeksView() {
        return weeksView;
    }

    /**
     * 设置滚动回调监听
     */
    public interface OnScrollListener{
        public void onScroll(int scrollY);
    }
    public interface OnDateSelect {
        public void onDateSelected(LocalDate date);
    }
    @Override
    public void onClick(View v) {

    }
}
