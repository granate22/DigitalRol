package digitalrol.android.com.digitalrol.model;

/**
 * Created by diego.mazzitelli on 28/06/2015.
 */
public class Status
{
    private int id;
    private String name;
    private boolean active;

    public Status (int id, String name, boolean active)
    {
        this.id=id;
        this.setName(name);
        this.active=active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
