package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;
import ro.pub.cs.systems.eim.myapplicationf.network.StergereContAsyncTask;

public class ContulMeuFragment  extends Fragment {

    ImageView buttonDateDeContact;
    ImageView buttonModificaParola;
    ImageView buttonLocuriDeConsum;
    ImageView buttonStergereCont;
    Button paginaPrincipala;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonDateDeContact = view.findViewById(R.id.cerc_datedecontact);
        buttonModificaParola = view.findViewById(R.id.cerc_modificaparola);
        buttonLocuriDeConsum = view.findViewById(R.id.cerc_locurideconsum);
        buttonStergereCont = view.findViewById(R.id.cerc_stergerecont);
        paginaPrincipala = view.findViewById(R.id.exit_contulmeu);

        buttonDateDeContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDeContactFragment dateDeContactFragment = new DateDeContactFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, dateDeContactFragment,DateDeContactFragment.class.getSimpleName().toString()).addToBackStack("datedecontact").commit();

            }
        });

        buttonModificaParola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificaParolaFragment modificareParolaFragment = new ModificaParolaFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, modificareParolaFragment,ModificaParolaFragment.class.getSimpleName().toString()).addToBackStack("modificaparola").commit();

            }
        });

        buttonLocuriDeConsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocuriDeConsumFragment locuriDeConsumFragment = new LocuriDeConsumFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, locuriDeConsumFragment,LocuriDeConsumFragment.class.getSimpleName().toString()).addToBackStack("locurideconsum").commit();

            }
        });

        buttonStergereCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new StergereContAsyncTask((MainActivity) getActivity()).execute();
            }
        });

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
