package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;
import cc.linktime.datetimewidget.util.CellEven;
import cc.linktime.datetimewidget.util.EvenBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/27/14
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeGrid extends ViewGroup {
    private final int maxRowCount = 24;
    private final int cellHeight =  getResources().getDimensionPixelSize(R.dimen.cell_height);
    private final int colHeight = cellHeight * maxRowCount;
    private int colWidth;
    private Paint divider;
    private Calendar cal;
    private ArrayList<EvenBundle> evenList;

    public DateTimeGrid(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
        evenList = new ArrayList<EvenBundle>();
    }

    public DateTimeGrid(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
        divider = new Paint();
        divider.setColor(getResources().getColor(R.color.cell_divider));
        cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        evenList = new ArrayList<EvenBundle>();
    }

    public DateTimeGrid(Context context, AttributeSet attrs, int defStyle) {
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

        //measure each col
        for (int c=0;c<getChildCount();c++){
            View child = getChildAt(c);
            child.measure(colWidth,colHeight);
        }
        setMeasuredDimension(totalWidth + getPaddingLeft(),cellHeight + colHeight + getPaddingBottom());
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        final boolean retVal = super.drawChild(canvas, child, drawingTime);    //To change body of overridden methods use File | Settings | File Templates.
        int bottom = colHeight;
        canvas.drawLine(child.getRight() - 1,child.getTop(),child.getRight() - 1, bottom,divider);
        return retVal;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        //ViewGroup row = (ViewGroup)getChildAt(0);

        ViewGroup rowHead = (ViewGroup)getChildAt(1);
        for (int c=0;c<rowHead.getChildCount();c++) {
            ((DateTimeCell)rowHead.getChildAt(c)).setRowHead(true);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.i("Grid","Grid:onLayout --- " + left + " " + top + " " + right + " " + bottom);
//        final int width = right - left;
        final int hight = colHeight;

        // layout each col
        for (int c=0,numCount=getChildCount();c<numCount;c++) {
            View child = getChildAt(c);
            child.layout(colWidth*c,0,colWidth*(c+1),colHeight);
        }

    }

    public void refreshContent(){
        //int firstDayOfWeek = cal.getFirstDayOfWeek();

        //init rowhead
        DateTimeCol rowCol = (DateTimeCol)getChildAt(0);
        for (int c=0;c<rowCol.getChildCount();c++){
            DateTimeCell rowhead = (DateTimeCell)rowCol.getChildAt(c);
            rowhead.setText(String.valueOf(c));
        }

        for(EvenBundle eb : evenList) {
            Calendar ebCal = eb.getCal();
            if (cal.get(Calendar.WEEK_OF_MONTH) == ebCal.get(Calendar.WEEK_OF_MONTH) &&
                cal.get(Calendar.MONTH) == ebCal.get(Calendar.MONTH) &&
                cal.get(Calendar.YEAR) == ebCal.get(Calendar.YEAR)
                    ) {
                CellEven ev = eb.getCellEven();
                DateTimeCell cell = getCell(ev.dayOfWeek,ev.startHour);
                cell.setText(ev.s);
                cell.setEven(true);
            }
        }

    }

    public DateTimeCell getCell(int col,int row){
        /**
         * row --- day of week : 1~7
         * col --- hour of day : 0~23
         */
        return (DateTimeCell)((ViewGroup)getChildAt(col)).getChildAt(row);
    }

    public Calendar getCal(){
        return (Calendar)cal.clone();
    }

    public void setEvenList(ArrayList<EvenBundle> evenList) {
        this.evenList = evenList;
    }

    public void setCalendar(Calendar cal){
        this.cal = cal;
    }

}
