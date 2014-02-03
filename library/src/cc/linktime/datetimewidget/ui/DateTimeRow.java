package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cc.linktime.datetimewidget.R;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/16/14
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeRow extends ViewGroup {
    private int cellSize;

    public DateTimeRow(Context context) {
        super(context);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeRow(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DateTimeRow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        Log.i("Row", "addView ---- Row");
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long start = System.currentTimeMillis();
        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        Log.i("Row","onMeasure --- totalWidth:" + totalWidth );
        cellSize = totalWidth / 8;
        Log.i("Row","onMeasure --- cellSize:" + cellSize );
        int cellWidthSpec = makeMeasureSpec(cellSize, EXACTLY);
        int cellHeightSpec = getResources().getDimensionPixelSize(R.dimen.cell_height);
        for (int c = 0, numChildren = getChildCount(); c < numChildren; c++) {
            final View child = getChildAt(c);
            child.measure(cellWidthSpec, cellHeightSpec);
        }
        final int widthWithPadding = totalWidth + getPaddingLeft() + getPaddingRight();
        final int heightWithPadding = cellHeightSpec + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthWithPadding, heightWithPadding);
    }

    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int cellHeight = bottom - top;
        for (int c = 0, numChildren = getChildCount(); c < numChildren; c++) {
            final View child = getChildAt(c);
            child.layout(c * cellSize, 0, (c + 1) * cellSize, cellHeight);
        }
    }
}
