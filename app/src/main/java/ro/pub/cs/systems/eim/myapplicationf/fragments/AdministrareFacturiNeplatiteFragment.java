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
import ro.pub.cs.systems.eim.myapplicationf.istoricindex_recyclerview.IstoricIndexAdapter;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllIstoricIndexesAsyncTask;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;

public class AdministrareFacturiNeplatiteFragment extends Fragment {

    Button listeazaButton;
    Button inapoiButton;
    Spinner locatii_istoricIndex;
    List<LocConsum> locuriConsum;
    List<FacturiNeplatite> dataset;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        locatii_istoricIndex = view.findViewById(R.id.spinner_locconsum_administrarefacturineplatite);

        locuriConsum = new ArrayList<>();
        dataset = new ArrayList<>();

        listeazaButton = view.findViewById(R.id.button_listeazafacturineplatite);
        inapoiButton = view.findViewById(R.id.exit_admnistrarefacturineplatite);

        listeazaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}
