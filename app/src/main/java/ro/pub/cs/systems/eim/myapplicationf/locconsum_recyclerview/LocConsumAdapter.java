package ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareLocuriDeConsumFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.EraseLocDeConsumTaskAsync;

public class LocConsumAdapter extends RecyclerView.Adapter<LocuriDeConsumViewHolder> {

    List<LocConsum> dataSet;
    AdministrareLocuriDeConsumFragment locuriDeConsumFragment;
    MainActivity mainActivity;

    public LocConsumAdapter(List<LocConsum> dataset, AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment,MainActivity mainActivity) {
        this.locuriDeConsumFragment = administrareLocuriDeConsumFragment;
        this.mainActivity = mainActivity;
        this.dataSet = dataset;
    }

    @Override
    public void onBindViewHolder(@NonNull LocuriDeConsumViewHolder holder, int position) {
        final int copyI = position;
        final LocConsum loc = dataSet.get(position);
        holder.adresa.setText(loc.getAddress());
        holder.city.setText(loc.getCity());
        holder.postalCode.setText(loc.getPostalcode());

        holder.eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ASTA TB INLOCUITA CU REMOVE PE SERVER
                ((MainActivity) mainActivity).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                new EraseLocDeConsumTaskAsync(mainActivity).execute(loc.getAddress(),loc.getCity(),loc.getPostalcode());
                dataSet.remove(copyI);
                notifyItemRemoved(copyI);
                notifyItemRangeChanged(copyI, dataSet.size());
            }
        });


    }

    @NonNull
    @Override
    public LocuriDeConsumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.administrare_locuriconsum_row,parent,false);
        return new LocuriDeConsumViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
