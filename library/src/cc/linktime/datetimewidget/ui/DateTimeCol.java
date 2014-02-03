package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/16/14
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeCol extends ViewGroup {
    private Paint divider;
    private int cellWidth;
    private int cellHeight;
    private int cellCount;

    public DateTimeCol(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeCol(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
        divider = new Paint();
        divider.setColor(R.color.cell_divider);
        cellHeight = getResources().getDimensionPixelSize(R.dimen.cell_height);
        cellCount = 4;
    }

    public DateTimeCol(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        Log.i("Col", "addView ---- " + ((DateTimeCell)child).getTextSize());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        Log.i("Col","Col:dispatchDraw --- ");
        for (int c=0; c<getChildCount();c++) {
            View child = getChildAt(c);
            canvas.drawLine(child.getLeft(),child.getBottom() - 1,child.getRight(), child.getBottom() - 1 ,divider);
        }
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long start = System.currentTimeMillis();
        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int totalHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("Col","onMeasure --- totalHeight:" + totalHeight );
        //cellWidth = totalWidth / 7;
        cellWidth = totalWidth;

        Log.i("Col","onMeasure --- cellWidth:" + cellWidth );
        int cellWidthSpec = makeMeasureSpec(cellWidth, EXACTLY);
        int cellHeightSpec = makeMeasureSpec(cellHeight, EXACTLY);
        for (int c = 0, numChildren = getChildCount(); c < numChildren; c++) {
            final View child = getChildAt(c);
            child.measure(cellWidthSpec, cellHeightSpec);
            // The Col height is the height of the tallest cell.
//            if (child.getMeasuredHeight() > ColHeight) {
//                ColHeight = child.getMeasuredHeight();
//            }
        }
        final int widthWithPadding = totalWidth + getPaddingLeft() + getPaddingRight();
        final int heightWithPadding = cellCount * cellHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthWithPadding, heightWithPadding);
    }

    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        long start = System.currentTimeMillis();
        int cellWidth = right - left;
        for (int c = 0, numChildren = getChildCount(); c < numChildren; c++) {
            final View child = getChildAt(c);
            Log.i("Col","Col:onLayout --- " + cellHeight * c );
            child.layout(0, c * cellHeight , cellWidth, (c + 1) * cellHeight);
        }
    }
}
