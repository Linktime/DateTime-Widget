package cc.linktime.datetimewidget.util;

/**
 * Created with IntelliJ IDEA.
 * User: freedom
 * Date: 2/4/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellEven {
    public int dayOfWeek;
    public int startHour;
    public int endHour;
    public String s;
    public CellEven(int dayOfWeek,int startHour,int endHour,String s) {
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
        this.s = s;
    }
}
