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
        holder.first.setText(factura.getFirstDay());
        holder.last.setText(factura.getLastDay());
        holder.value.setText(factura.getValue());

//
//        holder.eraseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //ASTA TB INLOCUITA CU REMOVE PE SERVER
//                ((MainActivity) mainActivity).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                new EraseLocDeConsumTaskAsync(mainActivity).execute(loc.getAddress(),loc.getCity(),loc.getPostalcode());
//                dataSet.remove(copyI);
//                notifyItemRemoved(copyI);
//                notifyItemRangeChanged(copyI, dataSet.size());
//            }
//        });


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
