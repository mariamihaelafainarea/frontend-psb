package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;

public class FacturiSiPlatiFragment extends Fragment {

    ImageView administrareFacturiNeplatite;
    ImageView listarePlati;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        administrareFacturiNeplatite = view.findViewById(R.id.cerc_administrarefacturineplatite);
        listarePlati = view.findViewById(R.id.cerc_listareplati);

        administrareFacturiNeplatite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdministrareFacturiNeplatiteFragment administrareFacturiNeplatiteFragment = new AdministrareFacturiNeplatiteFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, administrareFacturiNeplatiteFragment, AdministrareFacturiNeplatiteFragment.class.getSimpleName()).commit();

            }
        });

        listarePlati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListarePlatiFragment listarePlatiFragment = new ListarePlatiFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listarePlatiFragment,ListarePlatiFragment.class.getSimpleName()).commit();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facturi, container, false);
    }
}
