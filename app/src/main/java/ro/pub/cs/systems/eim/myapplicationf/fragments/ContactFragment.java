package ro.pub.cs.systems.eim.myapplicationf.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.pub.cs.systems.eim.myapplicationf.R;

public class ContactFragment extends Fragment {

    ImageView phoneImage;
    ImageView emailImage;
    ImageView linkedinImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneImage = view.findViewById(R.id.imageView3);
        emailImage = view.findViewById(R.id.imageView4);
        linkedinImage = view.findViewById(R.id.imageView5);

        TextView phone = view.findViewById(R.id.contact1);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722854299"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });

        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0722854299"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
            }
        });


        TextView email = view.findViewById(R.id.contact2);
        email.setOnClickListener(new View.OnClickListener() {
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

        TextView facebook = view.findViewById(R.id.contact3);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypaycompany"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        linkedinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://www.facebook.com/easypaycompany/"));
                if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });
    }
}
