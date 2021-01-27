package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.GetDateDeContactTaskAsync;

public class DateDeContactFragment extends Fragment {
    Button inapoiButton;
    EditText nume;
    EditText prenume;
    EditText email;
    EditText telefon;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inapoiButton = view.findViewById(R.id.exit_datecontact);

        nume = view.findViewById(R.id.edit_nume_datecontact);
        prenume = view.findViewById(R.id.edit_prenume_datecontact);
        email = view.findViewById(R.id.edit_email_datecontact);
        telefon = view.findViewById(R.id.edit_telefon_datecontact);


        new GetDateDeContactTaskAsync(((MainActivity)getActivity()), DateDeContactFragment.this).execute(((MainActivity)getActivity()).getJwtTokenCode());

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("datedecontact", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.datecontact, container, false);

    }

    public EditText getNume() {
        return nume;
    }

    public void setNume(EditText nume) {
        this.nume = nume;
    }

    public EditText getPrenume() {
        return prenume;
    }

    public void setPrenume(EditText prenume) {
        this.prenume = prenume;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getTelefon() {
        return telefon;
    }

    public void setTelefon(EditText telefon) {
        this.telefon = telefon;
    }
}
