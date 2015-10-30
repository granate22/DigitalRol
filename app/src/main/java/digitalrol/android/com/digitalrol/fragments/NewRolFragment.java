package digitalrol.android.com.digitalrol.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.adapters.CrewListViewAdapter;
import digitalrol.android.com.digitalrol.model.Constants;
import digitalrol.android.com.digitalrol.model.CrewMember;
import digitalrol.android.com.digitalrol.model.Ship;
import digitalrol.android.com.digitalrol.model.TimeConverterHelper;
import digitalrol.android.com.digitalrol.model.Waypoint;

/**
 * Created by diego.mazzitelli on 11/05/2015.
 */
public class NewRolFragment extends Fragment
{
    public static final String TAG = "NewRolFragment";
    private EditText timeRegreso, timeSalida, crewDNI, crewName;
    private TimePickerDialog tpSalida, tpRegreso;
    private SimpleDateFormat sdf;
    private List<Waypoint> waypointList;
    private List<Ship> shipList;
    private Spinner spShips, spWaypoints;
    private List<CrewMember> crewMembersList;
    private ListView lvCrewMembers;
    private ImageView imgAddCrew;
    private CrewListViewAdapter crewListViewAdapter;
    private Button btnGuardar;
    private boolean allIsLoaded=false;

    public static NewRolFragment newInstance()
    {
        NewRolFragment f = new NewRolFragment();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getActivity().getString(R.string.new_rol_title));
        getActivity().invalidateOptionsMenu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_rol_fragment, container, false);
        if (view != null)
        {
            timeSalida = (EditText) view.findViewById(R.id.time_salida);
            timeRegreso= (EditText) view.findViewById(R.id.time_regreso);
            spShips = (Spinner) view.findViewById(R.id.sp_nombre_barco);
            spWaypoints = (Spinner) view.findViewById(R.id.sp_destino);
            lvCrewMembers = (ListView) view.findViewById(R.id.lv_crew);
            imgAddCrew = (ImageView) view.findViewById(R.id.add_crew);
            crewDNI = (EditText) view.findViewById(R.id.et_crew_dni);
            crewName = (EditText) view.findViewById(R.id.et_nombre_crew);
            btnGuardar = (Button) view.findViewById(R.id.btn_guardar);

            loadData();
            Calendar cal = Calendar.getInstance();
            boolean is24HS=android.text.format.DateFormat.is24HourFormat(getActivity());
            sdf = TimeConverterHelper.getTimeSDF(is24HS);

            tpSalida = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Calendar calAux= Calendar.getInstance();
                    calAux.set(calAux.HOUR_OF_DAY,hourOfDay);
                    calAux.set(calAux.MINUTE,minute);
                    timeSalida.setText(sdf.format(calAux.getTime()));
                }
            },cal.get(cal.HOUR_OF_DAY), cal.get(cal.MINUTE),is24HS);

            tpRegreso = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calAux= Calendar.getInstance();
                calAux.set(calAux.HOUR_OF_DAY,hourOfDay);
                calAux.set(calAux.MINUTE,minute);
                timeRegreso.setText(sdf.format(calAux.getTime()));
            }
            },cal.get(cal.HOUR_OF_DAY), cal.get(cal.MINUTE),is24HS);

            timeSalida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus && allIsLoaded) {
                        timeSalida.performClick();
                    }
                }
            });
            timeSalida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tpSalida.show();
                }
            });

            timeRegreso.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus && allIsLoaded) {
                        timeRegreso.performClick();
                    }
                }
            });
            timeRegreso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tpRegreso.show();
                }
            });




            ArrayAdapter<Waypoint> waypointArrayAdapter = new ArrayAdapter<Waypoint>(getActivity(),
                    R.layout.spinner_item,waypointList);
            waypointArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spWaypoints.setAdapter(waypointArrayAdapter);


            ArrayAdapter<Ship> shipArrayAdapter = new ArrayAdapter<Ship>(getActivity(),
                    R.layout.spinner_item,shipList);
            shipArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spShips.setAdapter(shipArrayAdapter);

            crewMembersList = new ArrayList<CrewMember>();

            imgAddCrew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddCrewMember();
                }
            });

            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeFragment();
                }
            });
            allIsLoaded=true;
        }
        return view;
    }

    private void saveRol()
    {
        String timeDesde;
        String timeHasta;

        timeDesde = timeSalida.getText().toString();
    }

    private void closeFragment()
    {
        getActivity().finish();
    }

    private void AddCrewMember()
    {
        String name= crewName.getText().toString();
        String dni = crewDNI.getText().toString();

        if (name.length()==0)
        {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.new_rol_missing_name), Toast.LENGTH_SHORT).show();
            crewName.requestFocus();
            return;
        }

        if (dni.length()==0)
        {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.new_rol_missing_dni), Toast.LENGTH_SHORT).show();
            crewDNI.requestFocus();
            return;
        }

        crewMembersList.add(new CrewMember(Integer.valueOf(dni),name));
        crewListViewAdapter = new CrewListViewAdapter(getActivity().getApplicationContext(),crewMembersList);
        lvCrewMembers.setAdapter(crewListViewAdapter);
        lvCrewMembers.refreshDrawableState();

        crewName.setText("");
        crewDNI.setText("");

    }

    private void loadData()
    {
        int id=0;
        waypointList = new ArrayList<Waypoint>();
        waypointList.add(new Waypoint(id++,"Banco 5 Chalets","",""));
        waypointList.add(new Waypoint(id++,"Banco Afuera","",""));
        waypointList.add(new Waypoint(id++,"Banco Bacota","",""));
        waypointList.add(new Waypoint(id++,"Banco Camet","",""));
        waypointList.add(new Waypoint(id++,"Banco Cuarteles","",""));
        waypointList.add(new Waypoint(id++,"Banco del Medio","",""));
        waypointList.add(new Waypoint(id++,"Banco El Caballo","",""));
        waypointList.add(new Waypoint(id++,"Banco Elena","",""));
        waypointList.add(new Waypoint(id++,"Banco Pampita","",""));
        waypointList.add(new Waypoint(id++,"Banco Patria","",""));
        waypointList.add(new Waypoint(id++,"Banco Pescadores","",""));
        waypointList.add(new Waypoint(id++,"Banco Pierino","",""));
        waypointList.add(new Waypoint(id++,"Banco Rescal","",""));
        waypointList.add(new Waypoint(id++,"Banco Restano","",""));
        waypointList.add(new Waypoint(id++,"Banco Restinga Norte","",""));
        waypointList.add(new Waypoint(id++,"Banco Restinga Sur","",""));
        waypointList.add(new Waypoint(id++,"Banco Santa Clara","",""));
        waypointList.add(new Waypoint(id++,"Banco Tierra","",""));
        waypointList.add(new Waypoint(id++,"Banco Tio","",""));
        waypointList.add(new Waypoint(id++,"Banco Tres Cupulas","",""));
        waypointList.add(new Waypoint(id++,"Escollera Norte","",""));
        waypointList.add(new Waypoint(id++,"Escollera Sur","",""));

        id=0;
        shipList = new ArrayList<Ship>();
        shipList.add(new Ship(id++,"Santa Catarina I"));
        shipList.add(new Ship(id++,"Santa Catarina II"));
        shipList.add(new Ship(id++,"San Felipe"));

    }
}
