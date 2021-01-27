package ro.pub.cs.systems.eim.myapplicationf.istoricindex_recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class IstoricIndexViewHolder extends RecyclerView.ViewHolder {

    public TextView valoare;
    public TextView an;
    public TextView luna;

    public IstoricIndexViewHolder(@NonNull View itemView) {
        super(itemView);
        valoare =(TextView) itemView.findViewById(R.id.valoareindex);
        an = (TextView) itemView.findViewById(R.id.anindex);
        luna = (TextView) itemView.findViewById(R.id.lunaindex);

    }
}
