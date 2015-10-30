package digitalrol.android.com.digitalrol.model;

import android.content.Context;

import java.text.SimpleDateFormat;

/**
 * Created by diego.mazzitelli on 28/06/2015.
 */
public class TimeConverterHelper {
    
    public static SimpleDateFormat getTimeSDF(boolean is24HS)
    {
        SimpleDateFormat sdf;
        if (is24HS)
        {
            sdf = new SimpleDateFormat(Constants.TIME_FORMAT_24);
        }
        else
        {
            sdf = new SimpleDateFormat(Constants.TIME_FORMAT_12);
        }
        return sdf;
    }

    public static SimpleDateFormat getDateSDF()
    {
        return new SimpleDateFormat(Constants.DATE_FORMAT);
    }
}
