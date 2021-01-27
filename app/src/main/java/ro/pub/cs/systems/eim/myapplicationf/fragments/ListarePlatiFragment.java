package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.facturiplatite_recyclerview.facturineplatite_recyclerview.FacturiPlatiteAdaptor;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllIstoricIndexesAsyncTask;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllPlatiTaskAsync;

public class ListarePlatiFragment extends Fragment {

    Spinner locConsumSpinner;
    Button inapoiButton;
    ArrayList<LocConsum> locuriConsum;
    ArrayList<FacturiNeplatite> dataset;
    Button veziPlati;
    FacturiPlatiteAdaptor facturiPlatiteAdaptor;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locConsumSpinner = view.findViewById(R.id.spinner_locconsum_listareplati);
        inapoiButton = view.findViewById(R.id.exit_listareplati);
        veziPlati = view.findViewById(R.id.button_listareplati);


        locuriConsum = new ArrayList<>();
        dataset = new ArrayList<>();

        facturiPlatiteAdaptor = new FacturiPlatiteAdaptor(dataset, ListarePlatiFragment.this,(MainActivity)getActivity());
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_listareplati);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(facturiPlatiteAdaptor);

        new GetAllLocuriDeConsumTaskAsync((MainActivity)getActivity(),ListarePlatiFragment.this).execute(((MainActivity) getActivity()).getJwtTokenCode());

        veziPlati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = locConsumSpinner.getSelectedItemPosition();
                String address = locuriConsum.get(pos).getAddress();
                String city = locuriConsum.get(pos).getCity();
                String postalCode = locuriConsum.get(pos).getPostalcode();

                new GetAllPlatiTaskAsync((MainActivity)getActivity(),ListarePlatiFragment.this).execute(address,city,postalCode);
            }
        });




        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("listare_plati", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listare_plati, container, false);
    }

    public Spinner getLocConsumSpinner() {
        return locConsumSpinner;
    }

    public void setLocConsumSpinner(Spinner locConsumSpinner) {
        this.locConsumSpinner = locConsumSpinner;
    }

    public Button getInapoiButton() {
        return inapoiButton;
    }

    public void setInapoiButton(Button inapoiButton) {
        this.inapoiButton = inapoiButton;
    }

    public ArrayList<LocConsum> getLocuriConsum() {
        return locuriConsum;
    }

    public void setLocuriConsum(ArrayList<LocConsum> locuriConsum) {
        this.locuriConsum = locuriConsum;
    }

    public ArrayList<FacturiNeplatite> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<FacturiNeplatite> dataset) {
        this.dataset = dataset;
    }

    public FacturiPlatiteAdaptor getFacturiPlatiteAdaptor() {
        return facturiPlatiteAdaptor;
    }

    public void setFacturiPlatiteAdaptor(FacturiPlatiteAdaptor facturiPlatiteAdaptor) {
        this.facturiPlatiteAdaptor = facturiPlatiteAdaptor;
    }
}

