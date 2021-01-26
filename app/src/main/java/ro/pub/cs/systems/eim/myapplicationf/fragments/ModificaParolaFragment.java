package ro.pub.cs.systems.eim.myapplicationf.fragments;

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
import ro.pub.cs.systems.eim.myapplicationf.network.ChangePasswordTaskAsync;

public class ModificaParolaFragment extends Fragment {
    EditText parolaNouaEditText;
    EditText confirmaParolaEditText;
    Button modificaButton;
    Button inapoiButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parolaNouaEditText = view.findViewById(R.id.edit_parolanoua_changepassword);
        confirmaParolaEditText = view.findViewById(R.id.edit_confirmaparola_changepassword);
        modificaButton = view.findViewById(R.id.modifica_changepassword);
        inapoiButton = view.findViewById(R.id.exit_changepassword);

        modificaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChangePasswordTaskAsync(getActivity()).execute(
                        parolaNouaEditText.getText().toString(),
                        confirmaParolaEditText.getText().toString()
                );
            }
        });

        inapoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("modificaparola", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_password, container, false);
    }
}
