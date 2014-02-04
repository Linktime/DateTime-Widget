package cc.linktime.datetimewidget.util;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/4/14
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EvenBundle {
    private Calendar cal;
    private CellEven ev;
    public EvenBundle(Calendar cal,CellEven ev){
        this.cal = cal;
        this.ev = ev;
    }

    public Calendar getCal() {
        return cal;
    }

    public CellEven getCellEven() {
        return ev;
    }

}
