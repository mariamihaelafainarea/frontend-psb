package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.TrasnmitereIndexAsyncTask;

public class TransmitereIndexFragment extends Fragment {

    Button transmitereIndexButton;
    Spinner addressSpinner;
    EditText anEditText;
    Spinner lunaSpinner;
    EditText indexEditText;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        transmitereIndexButton = (Button) view.findViewById(R.id.transmitereIndexAcum);
        addressSpinner = view.findViewById(R.id.adress_spinner);
        anEditText = view.findViewById(R.id.an_edittext);
        lunaSpinner = view.findViewById(R.id.luna_spinner);
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


        transmitereIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String luna = lunaSpinner.getSelectedItem().toString();
                    String an = anEditText.getText().toString();
                    String adresa = addressSpinner.getSelectedItem().toString();
                    String index = indexEditText.getText().toString();
                    new TrasnmitereIndexAsyncTask((MainActivity)getActivity()).execute(adresa,index,luna,an);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transmitere_index, container, false);
    }
}
