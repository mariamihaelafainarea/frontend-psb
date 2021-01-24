package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class FacturiFragment extends Fragment {

    ImageView buttonAdministrareFacturiNeplatite;
    ImageView buttonListarePlati;
    Button paginaPrincipala;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonAdministrareFacturiNeplatite = view.findViewById(R.id.cerc_administrarefacturineplatite);
        buttonListarePlati = view.findViewById(R.id.cerc_listareplati);
        paginaPrincipala = view.findViewById(R.id.exit_facturi);

        buttonAdministrareFacturiNeplatite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdministrareFacturiNeplatiteFragment administrareFacturiNeplatiteFragment = new AdministrareFacturiNeplatiteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, administrareFacturiNeplatiteFragment, AdministrareFacturiNeplatiteFragment.class.getSimpleName()).addToBackStack("administrare_facturi_neplatite").commit();
            }
        });

        buttonListarePlati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListarePlatiFragment listarePlatiFragment = new ListarePlatiFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listarePlatiFragment, ListarePlatiFragment.class.getSimpleName()).addToBackStack("listare_plati").commit();
            }
        });

        paginaPrincipala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("facturi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facturi, container, false);
    }
}
