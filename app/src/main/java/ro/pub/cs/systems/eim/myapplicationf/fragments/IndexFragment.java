package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.content.Intent;
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

import ro.pub.cs.systems.eim.myapplicationf.LoginActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;

public class IndexFragment extends Fragment {

    ImageView buttonTransmitereIndex;
    ImageView buttonIstoricIndex;
    Button paginaPrincipala;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TransmitereIndexFragment transmitereIndexFragment = new TransmitereIndexFragment();

        buttonTransmitereIndex = view.findViewById(R.id.cerc_transmitereindex);
        buttonIstoricIndex = view.findViewById(R.id.cerc_istoricindex);
        paginaPrincipala = view.findViewById(R.id.exit_index);

        buttonTransmitereIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, transmitereIndexFragment, TransmitereIndexFragment.class.getSimpleName()).commit();

            }
        });

        buttonIstoricIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, transmitereIndexFragment, TransmitereIndexFragment.class.getSimpleName()).commit();

            }
        });

        paginaPrincipala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("index", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.index, container, false);
    }
}
