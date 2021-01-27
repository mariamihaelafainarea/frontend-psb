package ro.pub.cs.systems.eim.myapplicationf.facturineplatite_recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class FacturiNeplatiteViewHolder extends RecyclerView.ViewHolder {

    public TextView first;
    public TextView last;
    public TextView value;

    public FacturiNeplatiteViewHolder(@NonNull View itemView) {
        super(itemView);
        first =(TextView) itemView.findViewById(R.id.firstday_facturineplatite);
        last = (TextView) itemView.findViewById(R.id.lastday_facturineplatite);
        value = (TextView) itemView.findViewById(R.id.value_facturineplatite);

    }
}
