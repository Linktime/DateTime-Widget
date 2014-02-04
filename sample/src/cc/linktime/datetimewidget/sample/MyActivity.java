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
import cc.linktime.datetimewidget.util.CellOnClickListener;

import java.util.Calendar;
import java.util.TimeZone;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private DateTimeCell cell1;
    private DateTimeCell cell2;
    private LayoutInflater layoutInflater;
    private LinearLayout linearLayout;
    private DateTimeCell dy_cell;



    private Button show;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout = (LinearLayout)findViewById(R.id.layout);

//        show = (Button)findViewById(R.id.show);
//
//        cell1 = (DateTimeCell)findViewById(R.id.cell1);
//        cell1.setToday(false);
//        cell1.setColHead(true);
//        cell1.setHightLighted(false);
//        cell1.setSelectable(false);
//
//        cell2 = (DateTimeCell)findViewById(R.id.cell2);
//        cell2.setToday(true);
//        cell2.setHightLighted(false);
//        cell2.setSelectable(false);

        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        //cal1.set(Calendar.YEAR,Calendar.MONTH+1,Calendar.DATE,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
        System.out.println("Print --- " + cal1.get(Calendar.DAY_OF_MONTH));


        layoutInflater = getLayoutInflater();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        DateTimeGrid view = (DateTimeGrid)layoutInflater.inflate(R.layout.week_gird,null);
//        view.refreshContent();
//        view.getCell(2,2).setEven(true);
//        view.getCell(2,2).setText("上课");

//        Log.i("sample","Grid child count:" + ((ViewGroup)view).getChildCount());
//        for (int c = 0;c<((ViewGroup)view).getChildCount();c++) {
//            View row = ((ViewGroup)view).getChildAt(c);
//            Log.i("sample","Grid child child count:" + ((ViewGroup)row).getChildCount());
//            for (int cc = 0;cc<((ViewGroup)row).getChildCount();cc++) {
//                DateTimeCell child = (DateTimeCell)((ViewGroup) row).getChildAt(cc);
//                child.setSelectable(true);
//                child.setHightLighted(false);
//                child.setToday(false);
//                child.setText("DB");
//                Log.i("sample", "xxx" + cc);
//            }
//        }

//        View row = layoutInflater.inflate(R.layout.week_row,null);
//        ((ViewGroup) view).addView(row);
        CellOnClickListener.init(this);
//        linearLayout.addView(view);

        dy_cell = new DateTimeCell(this);
        dy_cell.setText("DY_DB");

        View week = layoutInflater.inflate(R.layout.scroll_week,null);
        linearLayout.addView(week);
//        linearLayout.addView(dy_cell,0,params);

//        pop.showAsDropDown(show);


    }
}
