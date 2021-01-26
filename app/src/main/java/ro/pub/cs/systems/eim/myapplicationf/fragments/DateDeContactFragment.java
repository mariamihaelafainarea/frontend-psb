package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.GetDateDeContactTaskAsync;

public class DateDeContactFragment extends Fragment {

    public EditText nume;
    public EditText prenume;
    public EditText email;
    public EditText telefon;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nume = view.findViewById(R.id.nume_edit);
        prenume = view.findViewById(R.id.prenume_edit);
        email = view.findViewById(R.id.email_edit);
        telefon = view.findViewById(R.id.telefon_edit);


        new GetDateDeContactTaskAsync(((MainActivity)getActivity()), DateDeContactFragment.this).execute(((MainActivity)getActivity()).getJwtTokenCode());
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
