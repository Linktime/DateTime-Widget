package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;
import cc.linktime.datetimewidget.util.EvenBundle;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/3/14
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeBody extends ViewGroup {
    private DateTimeGrid grid;
    private DateTimeRow colHead;
    private final int cellHeight = getResources().getDimensionPixelSize(R.dimen.cell_height);
    public DateTimeBody(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeBody(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeBody(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        colHead = (DateTimeRow)getChildAt(0);
        colHead.measure(width,cellHeight);

        grid = (DateTimeGrid)getChildAt(1);
        grid.measure(width,height - cellHeight);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //To change body of implemented methods use File | Settings | File Templates.
        colHead.layout(0,0,colHead.getMeasuredWidth(),cellHeight);
        grid.layout(0, cellHeight, grid.getMeasuredWidth(), cellHeight + grid.getMeasuredHeight());
    }

    public void refreshContent(){
        colHead.refreshContent();
        grid.refreshContent();
    }

    public Calendar getCal() {
        return colHead.getCal();
    }

    public void setCalendar(Calendar cal){
        grid.setCalendar(cal);
        colHead.setCalendar(cal);
    }

    public void setEvenList(ArrayList<EvenBundle> evenList){
        grid.setEvenList(evenList);
    }
}
