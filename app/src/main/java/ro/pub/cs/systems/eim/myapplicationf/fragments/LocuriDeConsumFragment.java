package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class LocuriDeConsumFragment extends Fragment {

    ImageView administrareLocuriDeConsum;
    ImageView adaugareLocDeConsum;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        administrareLocuriDeConsum = view.findViewById(R.id.cerc_administrarelocuriconsum);
        adaugareLocDeConsum = view.findViewById(R.id.cerc_adaugareloc);

        administrareLocuriDeConsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment = new AdministrareLocuriDeConsumFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                        administrareLocuriDeConsumFragment,
                        AdministrareLocuriDeConsumFragment.class.getSimpleName()).addToBackStack("administrare_locuriconsum").commit();

            }
        });


        adaugareLocDeConsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdaugareLocConsumFragment adaugareLocConsumFragment = new AdaugareLocConsumFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                        adaugareLocConsumFragment,
                        AdaugareLocConsumFragment.class.getSimpleName()).addToBackStack("adaugare_locconsum").commit();


            }
        });


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.locurideconsum, container, false);
    }
}
