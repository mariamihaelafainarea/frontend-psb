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

import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllIstoricIndexesAsyncTask;

public class IstoricIndexFragment  extends Fragment {

    Spinner locatii_istoricIndex;
    Button veziIstoricButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locatii_istoricIndex = view.findViewById(R.id.adress_spinner_istoric);
        veziIstoricButton = view.findViewById(R.id.afiseaza_index);

        veziIstoricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //trebuie sa aducem tranzactiile de pe server
                //si sa le pun in recyclerview
                new GetAllIstoricIndexesAsyncTask(getActivity()).execute();
            }
        });



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.istoric_index, container, false);
    }
}
