package cc.linktime.datetimewidget.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import cc.linktime.datetimewidget.ui.DateTimeCell;
import cc.linktime.datetimewidget.ui.DateTimeGrid;
import cc.linktime.datetimewidget.ui.DateTimeWeek;
import cc.linktime.datetimewidget.util.CalendarBuilder;
import cc.linktime.datetimewidget.util.CellEven;
import cc.linktime.datetimewidget.util.CellOnClickListener;
import cc.linktime.datetimewidget.util.EvenBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout = (LinearLayout)findViewById(R.id.layout);

        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Print --- " + cal1.get(Calendar.DAY_OF_MONTH));


        DateTimeWeek week = CalendarBuilder.builder(this);
        ArrayList<EvenBundle> evenList = new ArrayList<EvenBundle>();
        evenList.add(new EvenBundle(cal1, new CellEven(3,4,5,"我在上课")));
        week.setEvenList(1,evenList);
        linearLayout.addView(week);

    }
}
