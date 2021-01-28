package ro.pub.cs.systems.eim.myapplicationf.facturineplatite_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareFacturiNeplatiteFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareLocuriDeConsumFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.EraseLocDeConsumTaskAsync;
import ro.pub.cs.systems.eim.myapplicationf.network.PayTheBillTaskAsync;

public class FacturiNeplatiteAdaptor extends RecyclerView.Adapter<FacturiNeplatiteViewHolder> {

    List<FacturiNeplatite> dataSet;
    AdministrareFacturiNeplatiteFragment facturiNeplatite;
    MainActivity mainActivity;

    public FacturiNeplatiteAdaptor(List<FacturiNeplatite> dataset, AdministrareFacturiNeplatiteFragment facturiNeplatite, MainActivity mainActivity) {
        this.facturiNeplatite = facturiNeplatite;
        this.mainActivity = mainActivity;
        this.dataSet = dataset;
    }

    @Override
    public void onBindViewHolder(@NonNull FacturiNeplatiteViewHolder holder, int position) {
        final int copyI = position;
        final FacturiNeplatite factura = dataSet.get(position);
        holder.first.setText(String.valueOf(factura.getFirstDay()));
        holder.last.setText(String.valueOf(factura.getLastDay()));
        holder.value.setText(String.valueOf(factura.getValue()));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PayTheBillTaskAsync(mainActivity, facturiNeplatite).execute(dataSet.get(copyI).getFirstDay(),
                        dataSet.get(copyI).getLastDay(),
                        String.valueOf(dataSet.get(copyI).getValue()),
                        facturiNeplatite.getLocuriConsum().get(copyI).getAddress(),
                        facturiNeplatite.getLocuriConsum().get(copyI).getPostalcode(),
                        facturiNeplatite.getLocuriConsum().get(copyI).getCity(),
                        String.valueOf(copyI)
                );


            }
        });


    }

    @NonNull
    @Override
    public FacturiNeplatiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facturineplatite_row,parent,false);
        return new FacturiNeplatiteViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
