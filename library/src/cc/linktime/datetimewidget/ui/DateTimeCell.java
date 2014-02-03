package cc.linktime.datetimewidget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import cc.linktime.datetimewidget.R;
import cc.linktime.datetimewidget.util.CellOnClickListener;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/16/14
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeCell extends TextView {

    private CellOnClickListener listener;

    private static final int[] STATE_EVEN = {
            R.attr.state_even
    };
    private static final int[] STATE_HIGHLIGHTED = {
            R.attr.state_highlighted
    };
    private static final int[] STATE_SELECTABLE = {
            R.attr.state_selectable
    };

    private static final int[] STATE_ROWHEAD = {
            R.attr.state_rowhead
    };

    private static final int[] STATE_COLHEAD = {
            R.attr.state_colhead
    };

    private boolean isRowHead = false;
    private boolean isEven = false;
    private boolean isHightLighted = false;
    private boolean isSelectable = true;
    private boolean isColHead = false;

    public DateTimeCell(Context context) {
        super(context);
    }

    public DateTimeCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        listener = new CellOnClickListener();
        setOnClickListener(listener);
    }

    public DateTimeCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEven(boolean isEven) {
        this.isEven = isEven;
        refreshDrawableState();
    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
        refreshDrawableState();
    }

    public void setHightLighted(boolean isHighLighted) {
        this.isSelectable = isHighLighted;
        refreshDrawableState();
    }

    public void setRowHead(boolean isRowHead){
        this.isRowHead = isRowHead;
        refreshDrawableState();
    }

    public void setColHead(boolean isColHead){
        this.isColHead = isColHead;
        refreshDrawableState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace+5);
        if (isRowHead) {
            this.setOnClickListener(null);
            mergeDrawableStates(drawableState,STATE_ROWHEAD);
        }
        if (isColHead) {
            this.setOnClickListener(null);
            mergeDrawableStates(drawableState,STATE_COLHEAD);
        }
        if (isSelectable){
        mergeDrawableStates(drawableState,STATE_SELECTABLE);
        };
        if (isEven){
        mergeDrawableStates(drawableState,STATE_EVEN);
        };
        if (isHightLighted){
        mergeDrawableStates(drawableState,STATE_HIGHLIGHTED);
        };
        return drawableState;
    }
}
