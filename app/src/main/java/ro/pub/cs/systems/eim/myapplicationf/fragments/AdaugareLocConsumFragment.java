package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.AddLocDeConsumTaskAsync;

public class AdaugareLocConsumFragment extends Fragment {

    EditText adresaEditText;
    EditText codPostalEditText;
    EditText orasEditText;
    Button adaugaButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adresaEditText = view.findViewById(R.id.edit_adresa_adaugarelocconsum);
        codPostalEditText = view.findViewById(R.id.edit_codpostal_adaugarelocconsum);
        orasEditText = view.findViewById(R.id.edit_oras_adaugarelocconsum);
        adaugaButton = view.findViewById(R.id.button_adaugarelocconsum);

        adaugaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddLocDeConsumTaskAsync(getActivity()).execute(
                        adresaEditText.getText().toString(),
                        codPostalEditText.getText().toString(),
                        orasEditText.getText().toString()
                );
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adaugareloconsum, container, false);
    }
}
