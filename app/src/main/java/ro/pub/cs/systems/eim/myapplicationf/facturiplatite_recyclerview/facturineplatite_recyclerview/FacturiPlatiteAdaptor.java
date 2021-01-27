package ro.pub.cs.systems.eim.myapplicationf.facturiplatite_recyclerview.facturineplatite_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareFacturiNeplatiteFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.ListarePlatiFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;

public class FacturiPlatiteAdaptor extends RecyclerView.Adapter<FacturiPlatiteViewHolder> {

    List<FacturiNeplatite> dataSet;
    ListarePlatiFragment listarePlatiFragment;
    MainActivity mainActivity;

    public FacturiPlatiteAdaptor(List<FacturiNeplatite> dataset, ListarePlatiFragment listarePlatiFragment, MainActivity mainActivity) {
        this.listarePlatiFragment = listarePlatiFragment;
        this.mainActivity = mainActivity;
        this.dataSet = dataset;
    }

    @Override
    public void onBindViewHolder(@NonNull FacturiPlatiteViewHolder holder, int position) {
        final int copyI = position;
        final FacturiNeplatite factura = dataSet.get(position);
        holder.first.setText(String.valueOf(factura.getFirstDay()));
        holder.last.setText(String.valueOf(factura.getLastDay()));
        holder.value.setText(String.valueOf(factura.getValue()));
    }

    @NonNull
    @Override
    public FacturiPlatiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facturiplatite_row,parent,false);
        return new FacturiPlatiteViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
