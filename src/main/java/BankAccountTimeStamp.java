import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 6/10/13
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTimeStamp {
    Date timeStamp;

    public String getTimeStamp() {
        return timeStamp.toGMTString();
    }

    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }
}
