package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/26/14
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeWeek extends ViewGroup {
    private int width;
    private int height;
    private float startX;
    private float startY;
    private float lastY;
    private DateTimeBody before;
    private DateTimeBody current;
    private DateTimeBody next;
    public DateTimeWeek(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeWeek(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeWeek(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        before = (DateTimeBody)getChildAt(0);
        current = (DateTimeBody)getChildAt(1);
        next = (DateTimeBody)getChildAt(2);

        Calendar beforeCal = current.getCal();
        beforeCal.add(Calendar.WEEK_OF_MONTH,-1);
        before.setCalendar(beforeCal);
        Calendar nextCal = current.getCal();
        nextCal.add(Calendar.WEEK_OF_MONTH,1);
        next.setCalendar(nextCal);

        before.refreshContent();
        current.refreshContent();
        next.refreshContent();

        before.layout(left-width,top,right-width,bottom);
        current.layout(left, top, right, bottom);
        next.layout(left + width, top, right + width, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        for (int c=0;c<getChildCount();c++) {
            getChildAt(c).measure(width,height);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal =  super.onTouchEvent(event);    //To change body of overridden methods use File | Settings | File Templates.
        if (event.getAction() == MotionEvent.ACTION_MOVE)
        Log.i("Week","Week:onTouchEvent --- move");
        if (event.getAction() == MotionEvent.ACTION_SCROLL)
            Log.i("Week","Week:onTouchEvent --- scroll");
        if (event.getAction() == MotionEvent.ACTION_CANCEL)
            Log.i("Week","Week:onTouchEvent --- cancel");
        return retVal;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean retVal =  super.dispatchTouchEvent(ev);    //To change body of overridden methods use File | Settings | File Templates.
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                Log.i("Week","Week:dispatchTouchEvent --- startAt:" + startX);
                break;
            case MotionEvent.ACTION_MOVE:
                scrollTo((int)(startX - ev.getX()),0);
                scrollGrid((int)(startY - ev.getY() + lastY));
                break;
            case MotionEvent.ACTION_UP:
                lastY += startY - ev.getY();

                if (ev.getX()-startX>(width/2)){
                    scrollTo(-width,0);
                    scrollLeftGrid();   // exchange the three grid
                    scrollTo(0,0);
                    Log.i("Week","Week:dispatchTouchEvent --- scroll right");
                } else if (startX - ev.getX()>(width/2)) {
                    scrollTo(width,0);
                    scrollRightGrid();  // exchange the three grid
                    scrollTo(0,0);
                    Log.i("Week","Week:dispatchTouchEvent --- scroll left");
                } else {
                    scrollTo(0,0);
                }
                break;
            default:
                break;
        }

//        DateTimeGrid gird = (DateTimeGrid)((DateTimeBody)getChildAt(1)).getChildAt(1);
//        gird.dispatchTouchEvent(ev);
        return retVal;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void scrollLeftGrid(){
        removeView(next);
        addView(next,0);
        DateTimeBody temp;
        temp = current;
        current = before;
        before = next;
        next = temp;
        before.getCal().add(Calendar.WEEK_OF_MONTH,-3);
        before.refreshContent();
    }

    protected void scrollRightGrid(){
        removeView(before);
        addView(before);
        DateTimeBody temp;
        temp = current;
        current = next;
        next = before;
        before = temp;
        next.getCal().add(Calendar.WEEK_OF_MONTH,3);
        next.refreshContent();
    }

    public void scrollGrid(int y){
        for (int c=0;c<getChildCount();c++) {
            DateTimeGrid grid = (DateTimeGrid)(((DateTimeBody)getChildAt(c)).getChildAt(1));
            int maxY = grid.getHeight() - getHeight() + 2 * getResources().getDimensionPixelSize(R.dimen.cell_height);
            if (y<0) grid.scrollTo(0,0);
            else if (y>maxY) grid.scrollTo(0,maxY);
            else grid.scrollTo(0,y);
        }
    }
}
