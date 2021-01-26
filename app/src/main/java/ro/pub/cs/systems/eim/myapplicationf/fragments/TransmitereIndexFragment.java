package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;
import ro.pub.cs.systems.eim.myapplicationf.network.GetAllLocuriDeConsumTaskAsync;
import ro.pub.cs.systems.eim.myapplicationf.network.TrasnmitereIndexAsyncTask;

public class TransmitereIndexFragment extends Fragment {

    Button transmitereIndexButton;
    Button inapoiButton;

    Spinner addressSpinner;
    EditText indexEditText;
    Spinner anSpinner;
    Spinner lunaSpinner;

    List<String> dataset = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transmitereIndexButton = view.findViewById(R.id.button_transmitereindex);
        inapoiButton = view.findViewById(R.id.exit_transmitereindex);
        addressSpinner = view.findViewById(R.id.spinner_locconsum_transmitereindex);
        indexEditText = view.findViewById(R.id.edit_index_transmitereindex);
        anSpinner = (Spinner) view.findViewById(R.id.edit_an_transmitereindex);
        lunaSpinner = view.findViewById(R.id.spinner_luna_transmitereindex);


        new GetAllLocuriDeConsumTaskAsync((MainActivity)getActivity(),
                TransmitereIndexFragment.this).execute(((MainActivity)getActivity()).getJwtTokenCode());

        //SPINNER  AN

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        anSpinner.setAdapter(adapter);

        anSpinner.setSelection(0);
        anSpinner.setBackgroundColor(Color.parseColor("#a9a9a9"));


        //SPINNER LUNA

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
                    String an = anSpinner.getSelectedItem().toString();
                    String adresa = dataset.get(addressSpinner.getSelectedItemPosition());
                    String index = indexEditText.getText().toString();
                    new TrasnmitereIndexAsyncTask((MainActivity)getActivity()).execute(adresa,index,luna,an);
            }
        });

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("transmitere_index", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transmitere_index, container, false);
    }


    public Spinner getAddressSpinner(){
        return addressSpinner;
    }


    public List<String> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<String> data) {
        this.dataset = data;
    }
}
