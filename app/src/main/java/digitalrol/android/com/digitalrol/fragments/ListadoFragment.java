package digitalrol.android.com.digitalrol.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.activities.UpActivity;
import digitalrol.android.com.digitalrol.adapters.RecyclerAdapter;
import digitalrol.android.com.digitalrol.model.Rol;
import digitalrol.android.com.digitalrol.model.Status;

/**
 * Created by diego.mazzitelli on 28/06/2015.
 */
public class ListadoFragment extends Fragment {

    public static final String TAG = "ListadoFragment";
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<Rol> rolList;
    private FloatingActionButton floatingButton;

    public static ListadoFragment newInstance()
    {
        return new ListadoFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getActivity().getString(R.string.listado_title));
        getActivity().invalidateOptionsMenu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listado_fragment,container, false);

        fillData();

        floatingButton = (FloatingActionButton) view.findViewById(R.id.floating_button);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.lst_rols);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        recyclerAdapter = new RecyclerAdapter(rolList);
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    private void fillData()
    {
        rolList = new ArrayList<>();
        Rol rol;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        rol = new Rol();
        rol.setDate(Calendar.getInstance().getTime());
        rol.setStatus(new Status(1,"Activo",true));
        startTime.set(Calendar.HOUR_OF_DAY,13);
        rol.setStartTime(startTime);
        endTime.set(Calendar.HOUR_OF_DAY,22);
        rol.setEndTime(endTime);
        rolList.add(rol);


        Calendar days = Calendar.getInstance();
        days.set(2015,06,20);
        rol = new Rol();
        rol.setDate(days.getTime());
        rol.setStatus(new Status(2,"Cerrado",false));
        startTime.set(Calendar.HOUR_OF_DAY,9);
        rol.setStartTime(startTime);
        endTime.set(Calendar.HOUR_OF_DAY,15);
        rol.setEndTime(endTime);
        rolList.add(rol);

        days.set(2015,06,12);
        rol = new Rol();
        rol.setDate(days.getTime());
        rol.setStatus(new Status(2,"Cerrado",false));
        startTime.set(Calendar.HOUR_OF_DAY,7);
        rol.setStartTime(startTime);
        endTime.set(Calendar.HOUR_OF_DAY,16);
        rol.setEndTime(endTime);
        rolList.add(rol);

        days.set(2015,05,30);
        rol = new Rol();
        rol.setDate(days.getTime());
        rol.setStatus(new Status(2,"Cerrado",false));
        startTime.set(Calendar.HOUR_OF_DAY,10);
        rol.setStartTime(startTime);
        endTime.set(Calendar.HOUR_OF_DAY,19);
        rol.setEndTime(endTime);
        rolList.add(rol);


    }
}
