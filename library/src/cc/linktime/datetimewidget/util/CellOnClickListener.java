package cc.linktime.datetimewidget.util;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import cc.linktime.datetimewidget.R;
import cc.linktime.datetimewidget.ui.InfoPopup;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 1/29/14
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class CellOnClickListener implements View.OnClickListener {
    private static InfoPopup pop;
    private static PopupWindow backPop;

    public static void init(Activity activity){
        LayoutInflater layoutInflater = LayoutInflater.from(activity.getApplicationContext());
        View view = layoutInflater.inflate(R.layout.popup,null);
//        view.setBackgroundColor(Color.rgb(240, 240, 240));
        pop = new InfoPopup(view);
        pop.initPopup(activity);

        LinearLayout l = new LinearLayout(activity);
        l.setBackgroundColor(Color.argb(70,74,74,74));

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                backPop.dismiss();
                pop.setFocusable(false);
                pop.dismiss();
            }
        });
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        backPop = new PopupWindow(l,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static void setPopContextView(View v) {
        pop.setContentView(v);
    }

    @Override
    public void onClick(View v) {
        //To change body of implemented methods use File | Settings | File Templates.
        backPop.showAtLocation(v,Gravity.NO_GRAVITY,0,0);
        pop.showAtLocation(v, Gravity.BOTTOM,0,0);
        pop.setFocusable(true);
    }


}
