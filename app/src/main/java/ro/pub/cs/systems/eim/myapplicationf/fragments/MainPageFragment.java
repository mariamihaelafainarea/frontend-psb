package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.content.Intent;
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

import ro.pub.cs.systems.eim.myapplicationf.LoginActivity;
import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.R;

public class MainPageFragment extends Fragment {

    ImageView indexView;
    ImageView facturiView;
    ImageView contulMeuView;
    ImageView puncteView;
    ImageView contactView;
    ImageView logoutView;
    Button despreButton;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        indexView = (ImageView) view.findViewById(R.id.index);
        facturiView = (ImageView)view.findViewById(R.id.facturisiplati);
        contulMeuView = (ImageView)view.findViewById(R.id.contulmeu);
        puncteView = (ImageView)view.findViewById(R.id.puncte);
        contactView = (ImageView)view.findViewById(R.id.contact);
        logoutView = (ImageView)view.findViewById(R.id.logout);
        despreButton = view.findViewById(R.id.despre);

        indexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IndexFragment indexFragment = ((MainActivity)getActivity()).indexFragment;
                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, indexFragment).addToBackStack("index").commit();
            }
        });

        facturiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacturiFragment facturiFragment = ((MainActivity)getActivity()).facturiFragment;
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, facturiFragment, FacturiFragment.class.getSimpleName()).addToBackStack("facturi").commit();
            }
        });

        contulMeuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContulMeuFragment contulMeuFragment = ((MainActivity)getActivity()).contulMeuFragment;
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, contulMeuFragment, ContulMeuFragment.class.getSimpleName().toString()).addToBackStack("contulmeu").commit();
            }
        });

        puncteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PuncteFragment puncteFragment = ((MainActivity)getActivity()).puncteFragment;
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, puncteFragment, PuncteFragment.class.getSimpleName()).addToBackStack("puncte").commit();
            }
        });
        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactFragment contactFragment = ((MainActivity)getActivity()).contactFragment;
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, contactFragment, ContactFragment.class.getSimpleName()).addToBackStack("contact").commit();
            }
        });
        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        despreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DespreFragment despreFragment = ((MainActivity)getActivity()).despreFragment;
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, despreFragment, DespreFragment.class.getSimpleName()).addToBackStack("despre").commit();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page, container, false);

    }
}
