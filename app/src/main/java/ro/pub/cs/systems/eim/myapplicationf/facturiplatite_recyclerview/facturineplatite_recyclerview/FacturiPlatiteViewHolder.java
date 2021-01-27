package ro.pub.cs.systems.eim.myapplicationf.facturiplatite_recyclerview.facturineplatite_recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class FacturiPlatiteViewHolder extends RecyclerView.ViewHolder {

    public TextView first;
    public TextView last;
    public TextView value;

    public FacturiPlatiteViewHolder(@NonNull View itemView) {
        super(itemView);
        first =(TextView) itemView.findViewById(R.id.firstday_platite);
        last = (TextView) itemView.findViewById(R.id.lastday_platite);
        value = (TextView) itemView.findViewById(R.id.valoare_platite);

    }
}
