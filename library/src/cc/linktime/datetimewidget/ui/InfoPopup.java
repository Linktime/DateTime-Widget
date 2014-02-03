package cc.linktime.datetimewidget.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import cc.linktime.datetimewidget.R;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/29/14
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class InfoPopup extends PopupWindow {
    private DisplayMetrics metrics;
    private int screenWidth;
    private int screenHeight;

    public InfoPopup(View contentView) {
        super(contentView);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public InfoPopup(Context context, AttributeSet attrs) {
        super(context, attrs);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public InfoPopup(View contentView, int width, int height) {
        super(contentView, width, height);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void initMetrics(Activity activity) {
        metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    public void initPopup(Activity activity){
        initMetrics(activity);
        setHeight(screenHeight/3);
        setWidth(screenWidth);
        setAnimationStyle(R.style.Pop);

    }



}
