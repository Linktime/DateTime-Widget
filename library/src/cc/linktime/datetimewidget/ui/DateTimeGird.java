package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/27/14
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeGird extends ViewGroup {
    private final int maxRowCount = 4;
    private final int childHeight = getResources().getDimensionPixelSize(R.dimen.cell_height) * maxRowCount;
    private int colWidth;
    private Paint divider;
    private Calendar cal;
    private final String[] week = {getResources().getString(R.string.sun),getResources().getString(R.string.mon),
            getResources().getString(R.string.tue),getResources().getString(R.string.wed),
            getResources().getString(R.string.thu),getResources().getString(R.string.wed),
            getResources().getString(R.string.sat)};


    public DateTimeGird(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeGird(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
        divider = new Paint();
        divider.setColor(getResources().getColor(R.color.cell_divider));
        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        cal.add(Calendar.DAY_OF_WEEK,1);
    }

    public DateTimeGird(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);    //To change body of overridden methods use File | Settings | File Templates.
        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        colWidth = totalWidth/8;
        for (int c=0;c<getChildCount();c++){
            View child = getChildAt(c);
            child.measure(colWidth,childHeight);
        }
        setMeasuredDimension(totalWidth + getPaddingLeft(),childHeight + getPaddingBottom());
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        final boolean retVal = super.drawChild(canvas, child, drawingTime);    //To change body of overridden methods use File | Settings | File Templates.
        int bottom = childHeight;
        canvas.drawLine(child.getRight() - 1,child.getTop() + getResources().getDimensionPixelSize(R.dimen.cell_height),child.getRight() - 1, bottom,divider);
        return retVal;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        //ViewGroup row = (ViewGroup)getChildAt(0);
        ViewGroup col = (ViewGroup)getChildAt(0);
        for (int c=0;c<col.getChildCount();c++) {
            ((DateTimeCell)col.getChildAt(c)).setRowHead(true);
        }

        for (int c=0,numCount=getChildCount()-1;c<numCount;c++){
            col = (DateTimeCol)getChildAt(c+1);
            DateTimeCell child = (DateTimeCell)col.getChildAt(0);
            child.setColHead(true);
        }

        Log.i("Grid","Grid:dispatchDraw --- divider");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.i("Grid","Grid:onLayout --- " + left + " " + top + " " + right + " " + bottom);
//        final int width = right - left;
        final int hight = childHeight;
        for (int c=0,numCount=getChildCount();c<numCount;c++) {
            View child = getChildAt(c);
            child.layout(colWidth*c,0,colWidth*(c+1),childHeight);
        }

    }

    public void refreshContent(){
        //int firstDayOfWeek = cal.getFirstDayOfWeek();
        int todayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        Calendar tempCal = (Calendar)cal.clone();

        //init rowhead
        DateTimeCol rowCol = (DateTimeCol)getChildAt(0);
        DateTimeCell rowColHead = (DateTimeCell)rowCol.getChildAt(0);
        rowColHead.setColHead(true);
        rowColHead.setText(tempCal.get(Calendar.YEAR) + "年" + (tempCal.get(Calendar.MONTH)+1) + "月");

        for (int c=1;c<rowCol.getChildCount();c++){
            DateTimeCell rowhead = (DateTimeCell)rowCol.getChildAt(c);
            rowhead.setText(String.valueOf(c));
        }

        // init colhead
        tempCal.add(Calendar.DAY_OF_WEEK,-todayOfWeek+1);   // set cal to the first day of week
        for (int c=0,numCount=getChildCount()-1;c<numCount;c++){
            DateTimeCol col = (DateTimeCol)getChildAt(c+1);
            DateTimeCell child = (DateTimeCell)col.getChildAt(0);
            //child.setText(String.valueOf(firstDayOfWeek+c) + "--" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)+c));
            child.setText(week[c] + " " + String.valueOf(tempCal.get(Calendar.DAY_OF_MONTH)));
            tempCal.add(Calendar.DAY_OF_MONTH,1);

        }
    }

    public DateTimeCell getCell(int col,int row){
        return (DateTimeCell)((ViewGroup)getChildAt(col)).getChildAt(row);
    }

    public Calendar getCal(){
        return (Calendar)cal.clone();
    }

    public void setCalendar(Calendar cal){
        this.cal = cal;
    }

}
