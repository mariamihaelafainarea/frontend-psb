package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.graphics.Color;
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
import ro.pub.cs.systems.eim.myapplicationf.facturineplatite_recyclerview.FacturiNeplatiteAdaptor;
import ro.pub.cs.systems.eim.myapplicationf.istoricindex_recyclerview.IstoricIndexAdapter;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllFacturiNeplatiteAsyncTask;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllIstoricIndexesAsyncTask;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;

public class AdministrareFacturiNeplatiteFragment extends Fragment {

    Button listeazaButton;
    Button inapoiButton;
    Spinner administrareFacturiSpinner;
    List<LocConsum> locuriConsum;
    List<FacturiNeplatite> dataset;

    FacturiNeplatiteAdaptor facturiNeplatiteAdaptor;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        administrareFacturiSpinner = view.findViewById(R.id.spinner_locconsum_administrarefacturineplatite);
        locuriConsum = new ArrayList<>();
        dataset = new ArrayList<>();
        listeazaButton = view.findViewById(R.id.button_listeazafacturineplatite);
        inapoiButton = view.findViewById(R.id.exit_admnistrarefacturineplatite);


        facturiNeplatiteAdaptor = new FacturiNeplatiteAdaptor(dataset, AdministrareFacturiNeplatiteFragment.this,(MainActivity)getActivity());
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_administrarefacturineplatite);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(facturiNeplatiteAdaptor);




        new GetAllLocuriDeConsumTaskAsync((MainActivity)getActivity(),
                AdministrareFacturiNeplatiteFragment.this).execute(((MainActivity)getActivity()).getJwtTokenCode());


        listeazaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = administrareFacturiSpinner.getSelectedItemPosition();
                String address = locuriConsum.get(pos).getAddress();
                String city = locuriConsum.get(pos).getCity();
                String postalCode = locuriConsum.get(pos).getPostalcode();

                new GetAllFacturiNeplatiteAsyncTask((MainActivity)getActivity(),AdministrareFacturiNeplatiteFragment.this).execute(address,city,postalCode);
            }
        });

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("administrare_facturi_neplatite", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.administrare_facturi_neplatite, container, false);
    }

    public Button getListeazaButton() {
        return listeazaButton;
    }

    public void setListeazaButton(Button listeazaButton) {
        this.listeazaButton = listeazaButton;
    }

    public Button getInapoiButton() {
        return inapoiButton;
    }

    public void setInapoiButton(Button inapoiButton) {
        this.inapoiButton = inapoiButton;
    }

    public Spinner getAdministrareFacturiSpinner() {
        return administrareFacturiSpinner;
    }

    public void setAdministrareFacturiSpinner(Spinner administrareFacturiSpinner) {
        this.administrareFacturiSpinner = administrareFacturiSpinner;
    }

    public List<LocConsum> getLocuriConsum() {
        return locuriConsum;
    }

    public void setLocuriConsum(List<LocConsum> locuriConsum) {
        this.locuriConsum = locuriConsum;
    }

    public List<FacturiNeplatite> getDataset() {
        return dataset;
    }

    public void setDataset(List<FacturiNeplatite> dataset) {
        this.dataset = dataset;
    }

    public FacturiNeplatiteAdaptor getFacturiNeplatiteAdaptor() {
        return facturiNeplatiteAdaptor;
    }

    public void setFacturiNeplatiteAdaptor(FacturiNeplatiteAdaptor facturiNeplatiteAdaptor) {
        this.facturiNeplatiteAdaptor = facturiNeplatiteAdaptor;
    }
}
