package cc.linktime.datetimewidget.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import cc.linktime.datetimewidget.R;
import cc.linktime.datetimewidget.ui.DateTimeWeek;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/4/14
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarBuilder {
    public static DateTimeWeek builder(Context context){
        Activity activity = (Activity)context;
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        DateTimeWeek week = (DateTimeWeek)layoutInflater.inflate(R.layout.scroll_week,null);
        CellOnClickListener.init(activity);
        return week;
    }
}
