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
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.AddLocDeConsumTaskAsync;

public class AdaugareLocConsumFragment extends Fragment {
    public EditText getAdresaEditText() {
        return adresaEditText;
    }

    public EditText getCodPostalEditText() {
        return codPostalEditText;
    }

    public EditText getOrasEditText() {
        return orasEditText;
    }

    EditText adresaEditText;
    EditText codPostalEditText;
    EditText orasEditText;
    Button adaugaButton;
    Button inapoiButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adresaEditText = view.findViewById(R.id.edit_adresa_adaugarelocconsum);
        codPostalEditText = view.findViewById(R.id.edit_codpostal_adaugarelocconsum);
        orasEditText = view.findViewById(R.id.edit_oras_adaugarelocconsum);
        adaugaButton = view.findViewById(R.id.button_adaugarelocconsum);
        inapoiButton = view.findViewById(R.id.exit_adaugarelocconsum);

        adaugaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddLocDeConsumTaskAsync(getActivity(), AdaugareLocConsumFragment.this).execute(
                        adresaEditText.getText().toString(),
                        codPostalEditText.getText().toString(),
                        orasEditText.getText().toString()
                );
            }
        });

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("adaugare_locconsum", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adaugareloconsum, container, false);
    }
}
