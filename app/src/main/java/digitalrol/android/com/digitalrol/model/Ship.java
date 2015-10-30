package digitalrol.android.com.digitalrol.model;

/**
 * Created by diego.mazzitelli on 14/05/2015.
 */
public class Ship
{
    private int id;
    private String name;

    public Ship(int id, String name)
    {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return this.name;
    }
}
