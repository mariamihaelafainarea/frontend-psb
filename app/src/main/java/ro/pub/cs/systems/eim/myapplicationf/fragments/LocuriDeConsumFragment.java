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

public class LocuriDeConsumFragment extends Fragment {

    Button inapoiButton;
    ImageView administrareLocuriDeConsum;
    ImageView adaugareLocDeConsum;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inapoiButton = view.findViewById(R.id.exit_locuriconsum);
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

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("locurideconsum", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.locurideconsum, container, false);
    }
}
