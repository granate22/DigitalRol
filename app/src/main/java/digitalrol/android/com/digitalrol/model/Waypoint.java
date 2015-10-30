package digitalrol.android.com.digitalrol.model;

/**
 * Created by diego.mazzitelli on 17/05/2015.
 */
public class Waypoint {
    private int id;
    private String name;
    private String latitude;
    private String longitude;

    public Waypoint()
    {

    }

    public Waypoint(int id, String name, String latitude, String longitude)
    {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.setLongitude(longitude);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
