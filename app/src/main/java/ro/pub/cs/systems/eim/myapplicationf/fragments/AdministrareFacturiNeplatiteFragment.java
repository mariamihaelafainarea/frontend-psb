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

import ro.pub.cs.systems.eim.myapplicationf.R;

public class AdministrareFacturiNeplatiteFragment extends Fragment {

    Spinner lunaSpinner;
    Button inapoiButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lunaSpinner = view.findViewById(R.id.spinner_luna_administrarefacturineplatite);
        inapoiButton = view.findViewById(R.id.exit_admnistrarefacturineplatite);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getActivity(), R.array.lunidinan,
                        android.R.layout.simple_spinner_item);
        lunaSpinner.setPrompt("Selecteaza luna!");
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lunaSpinner.setAdapter(staticAdapter);
        String ianuarie = "Ianuarie";
        int spinnerPosition = staticAdapter.getPosition(ianuarie);
        lunaSpinner.setSelection(spinnerPosition);
        lunaSpinner.setBackgroundColor(Color.parseColor("#a9a9a9"));

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
