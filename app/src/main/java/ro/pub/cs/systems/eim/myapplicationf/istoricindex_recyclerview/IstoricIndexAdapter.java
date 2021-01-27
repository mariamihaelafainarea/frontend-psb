package ro.pub.cs.systems.eim.myapplicationf.istoricindex_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareLocuriDeConsumFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.IstoricIndexFragment;
import ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview.LocuriDeConsumViewHolder;
import ro.pub.cs.systems.eim.myapplicationf.models.IstoricIndex;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.EraseLocDeConsumTaskAsync;

public class IstoricIndexAdapter extends RecyclerView.Adapter<IstoricIndexViewHolder> {

    List<IstoricIndex> dataSet;
    IstoricIndexFragment istoricIndexFragment;
    MainActivity mainActivity;

    public IstoricIndexAdapter(List<IstoricIndex> dataset, IstoricIndexFragment istoricIndexFragment, MainActivity mainActivity) {
        this.istoricIndexFragment = istoricIndexFragment;
        this.mainActivity = mainActivity;
        this.dataSet = dataset;
    }

    @Override
    public void onBindViewHolder(@NonNull IstoricIndexViewHolder holder, int position) {
        final int copyI = position;
        final IstoricIndex loc = dataSet.get(position);
        holder.valoare.setText(loc.getValue());
        holder.an.setText(loc.getYear());
        holder.luna.setText(loc.getMonth());
    }

    @NonNull
    @Override
    public IstoricIndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.istoric_index_row,parent,false);
        return new IstoricIndexViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
