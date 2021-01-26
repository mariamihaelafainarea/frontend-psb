package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview.LocConsumAdapter;
import ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview.LocuriDeConsumViewHolder;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;

public class AdministrareLocuriDeConsumFragment extends Fragment {

    List<LocConsum> dataset;
    LocConsumAdapter locConsumAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataset = new ArrayList<>();
        locConsumAdapter = new LocConsumAdapter(dataset, AdministrareLocuriDeConsumFragment.this,(MainActivity)getActivity());
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.locurideconsum_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(locConsumAdapter);


        new GetAllLocuriDeConsumTaskAsync((MainActivity)getActivity(),AdministrareLocuriDeConsumFragment.this).execute(((MainActivity) getActivity()).getJwtTokenCode());

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.administrare_locuriconsum, container, false);
    }

    public List<LocConsum> getDataset() {
        return dataset;
    }

    public void setDataset(List<LocConsum> dataset) {
        this.dataset = dataset;
    }

    public LocConsumAdapter getLocConsumAdapter() {
        return locConsumAdapter;
    }

    public void setLocConsumAdapter(LocConsumAdapter locConsumAdapter) {
        this.locConsumAdapter = locConsumAdapter;
    }
}
