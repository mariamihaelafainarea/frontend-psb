package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.istoricindex_recyclerview.IstoricIndexAdapter;
import ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview.LocConsumAdapter;
import ro.pub.cs.systems.eim.myapplicationf.models.IstoricIndex;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllIstoricIndexesAsyncTask;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;

public class IstoricIndexFragment  extends Fragment {

    Spinner locatii_istoricIndex;
    Button veziIstoricButton;
    Button inapoiButton;
    IstoricIndexAdapter istoricIndexAdapter;
    public List<IstoricIndex> dataset;
    public List<LocConsum> locuriConsum;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locatii_istoricIndex = view.findViewById(R.id.spinner_locconsum_istoricindex);
        veziIstoricButton = view.findViewById(R.id.button_istoricindex);
        inapoiButton = view.findViewById(R.id.exit_istoricindex);

        locuriConsum = new ArrayList<>();
        dataset = new ArrayList<>();

        istoricIndexAdapter = new IstoricIndexAdapter(dataset, IstoricIndexFragment.this,(MainActivity)getActivity());
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.istoricindex_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(istoricIndexAdapter);


        new GetAllLocuriDeConsumTaskAsync((MainActivity)getActivity(),IstoricIndexFragment.this).execute(((MainActivity) getActivity()).getJwtTokenCode());



        veziIstoricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = locatii_istoricIndex.getSelectedItemPosition();
                String address = locuriConsum.get(pos).getAddress();
                String city = locuriConsum.get(pos).getCity();
                String postalCode = locuriConsum.get(pos).getPostalcode();

                new GetAllIstoricIndexesAsyncTask((MainActivity)getActivity(),IstoricIndexFragment.this).execute(address,city,postalCode);
            }
        });

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("istoric_index", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.istoric_index, container, false);
    }

    public Spinner getLocatii_istoricIndex() {
        return locatii_istoricIndex;
    }

    public void setLocatii_istoricIndex(Spinner locatii_istoricIndex) {
        this.locatii_istoricIndex = locatii_istoricIndex;
    }

    public Button getVeziIstoricButton() {
        return veziIstoricButton;
    }

    public void setVeziIstoricButton(Button veziIstoricButton) {
        this.veziIstoricButton = veziIstoricButton;
    }

    public Button getInapoiButton() {
        return inapoiButton;
    }

    public void setInapoiButton(Button inapoiButton) {
        this.inapoiButton = inapoiButton;
    }

    public List<IstoricIndex> getDataset() {
        return dataset;
    }

    public void setDataset(List<IstoricIndex> dataset) {
        this.dataset = dataset;
    }

    public IstoricIndexAdapter getIstoricIndexAdapter() {
        return istoricIndexAdapter;
    }

    public void setIstoricIndexAdapter(IstoricIndexAdapter istoricIndexAdapter) {
        this.istoricIndexAdapter = istoricIndexAdapter;
    }


    public List<LocConsum> getLocuriConsum() {
        return locuriConsum;
    }

    public void setLocuriConsum(List<LocConsum> locuriConsum) {
        this.locuriConsum = locuriConsum;
    }
}
