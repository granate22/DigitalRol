package digitalrol.android.com.digitalrol.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.model.Rol;

/**
 * Created by diego.mazzitelli on 28/06/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<Rol> rolList;
    private Context context;

    public RecyclerAdapter(List<Rol> rols)
    {
        this.rolList= rols;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false);
        this.context=viewGroup.getContext();
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvDiayHora.setText("Día: "+rolList.get(i).getDiayHora(context));
        viewHolder.tvEstado.setText("Estado: "+rolList.get(i).getStatus().getName());
        if (rolList.get(i).getStatus().isActive())
        {
            viewHolder.imgFondo.setImageResource(R.drawable.open_rol);
            viewHolder.tvEstado.setTextColor(context.getResources().getColor(R.color.accent));
        }
        else
        {
            viewHolder.imgFondo.setImageResource(R.drawable.closed_rol);
            viewHolder.tvEstado.setTextColor(context.getResources().getColor(R.color.divider));
        }

    }

    @Override
    public int getItemCount() {
        return rolList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgFondo;
        public TextView tvDiayHora, tvEstado;
        public LinearLayout llRoot;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvDiayHora = (TextView) itemLayoutView.findViewById(R.id.tv_diayhora);
            tvEstado = (TextView) itemLayoutView.findViewById(R.id.tv_estado);
            imgFondo = (ImageView) itemLayoutView.findViewById(R.id.img_rol);
            llRoot = (LinearLayout) itemLayoutView.findViewById(R.id.card_list);
        }
    }
}
