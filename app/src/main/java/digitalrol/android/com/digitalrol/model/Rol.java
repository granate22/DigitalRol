package digitalrol.android.com.digitalrol.model;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by diego.mazzitelli on 17/05/2015.
 */
public class Rol
{
    private Date date;
    private CrewMember crewMember;
    private Ship ship;
    private Calendar startTime;
    private Calendar endTime;
    private Status status;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDiayHora(Context context)
    {
        String diayhora="";
        boolean is24HS=android.text.format.DateFormat.is24HourFormat(context);
        SimpleDateFormat sdfTime, sdfDate;
        sdfDate=TimeConverterHelper.getDateSDF();
        sdfTime = TimeConverterHelper.getTimeSDF(is24HS);

        if (date != null)
        {
            diayhora=sdfDate.format(date);
        }
        if (startTime!=null)
        {
            diayhora+="  de "+sdfTime.format(startTime.getTime());
        }
        if (endTime!=null)
        {
            diayhora+=" a "+sdfTime.format(endTime.getTime());
        }

        return diayhora;

    }
}
