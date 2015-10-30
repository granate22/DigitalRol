package digitalrol.android.com.digitalrol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.customviews.TextView_Roboto;
import digitalrol.android.com.digitalrol.model.CrewMember;

/**
 * Created by diego.mazzitelli on 17/05/2015.
 */
public class CrewListViewAdapter extends ArrayAdapter<CrewMember>
{
    private Context context;
    private List<CrewMember> crewMemberList;
    private LayoutInflater inflater;
    private CrewViewHolder holder;
    private CrewMember crewMember;

    public CrewListViewAdapter(Context context, List<CrewMember> crewMemberList)
    {
        super(context, 0, crewMemberList);
        this.crewMemberList = crewMemberList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        crewMember = crewMemberList.get(position);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_row_image,null,false);
            holder = new CrewViewHolder();
            holder.textViewNombre = (TextView) convertView.findViewById(R.id.tv_crew_nombre);
            holder.textViewDNI = (TextView) convertView.findViewById(R.id.tv_crew_dni);
        }

        holder.textViewDNI.setText("Dni:" + String.valueOf(crewMember.getDni()));
        holder.textViewNombre.setText("Nombre: "+crewMember.getName());

        return convertView;
    }

    static class CrewViewHolder
    {
        TextView textViewNombre;
        TextView textViewDNI;
    }
}
