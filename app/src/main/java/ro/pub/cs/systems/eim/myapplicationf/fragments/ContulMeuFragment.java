package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class ContulMeuFragment  extends Fragment {

    Button paginaPrincipala;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        paginaPrincipala = view.findViewById(R.id.exit);

        paginaPrincipala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("contulmeu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contulmeu, container, false);
    }
}
