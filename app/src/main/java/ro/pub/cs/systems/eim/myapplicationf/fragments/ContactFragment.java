package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class ContactFragment extends Fragment {

    ImageView phoneImage, phoneArrow;
    ImageView emailImage, emailArrow;
    ImageView linkedinImage, linkedinArrow;

    TextView phoneLabel, phoneMsg;
    TextView emailLabel, emailMsg;
    TextView linkedinLabel, linkedinMsg;

    Button exitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneImage = view.findViewById(R.id.phone_contact);
        emailImage = view.findViewById(R.id.mail_contact);
        linkedinImage = view.findViewById(R.id.fb_contact);

        phoneArrow = view.findViewById(R.id.phone_arrow_contact);
        emailArrow = view.findViewById(R.id.mail_arrow_contact);
        linkedinArrow = view.findViewById(R.id.fb_arrow_contact);

        phoneLabel = view.findViewById(R.id.phone_label_contact);
        emailLabel = view.findViewById(R.id.mail_label_contact);
        linkedinLabel = view.findViewById(R.id.fb_label_contact);

        phoneMsg = view.findViewById(R.id.phone_msg_contact);
        emailMsg = view.findViewById(R.id.mail_msg_contact);
        linkedinMsg = view.findViewById(R.id.fb_msg_contact);

        exitButton = view.findViewById(R.id.exit_contact);

        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722945571"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });

        phoneArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722945571"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });

        phoneLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722945571"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });

        phoneMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722945571"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });

        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:office@easypay.com" +
                        "?cc=" + "alice@example.com" +
                        "&subject=" + Uri.encode("No subject") +
                        "&body=" + Uri.encode("Hei you!");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
            }

        });

        emailArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:office@easypay.com" +
                        "?cc=" + "alice@example.com" +
                        "&subject=" + Uri.encode("No subject") +
                        "&body=" + Uri.encode("Hei you!");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
            }

        });

        emailLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:office@easypay.com" +
                        "?cc=" + "alice@example.com" +
                        "&subject=" + Uri.encode("No subject") +
                        "&body=" + Uri.encode("Hei you!");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
            }

        });

        emailMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:office@easypay.com" +
                        "?cc=" + "alice@example.com" +
                        "&subject=" + Uri.encode("No subject") +
                        "&body=" + Uri.encode("Hei you!");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
            }

        });

        linkedinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypay.company"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        linkedinArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypay.company/"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        linkedinLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypay.company/"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        linkedinMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypay.company/"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = (FragmentManager) getActivity().getSupportFragmentManager();
                fm.popBackStack ("contact", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }
}
