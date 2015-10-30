package digitalrol.android.com.digitalrol.model;

/**
 * Created by diego.mazzitelli on 17/05/2015.
 */
public class CrewMember
{
    private int dni;
    private String name;

    public CrewMember(int dni, String name)
    {
        this.setDni(dni);
        this.setName(name);
    }


    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
