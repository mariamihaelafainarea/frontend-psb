package ro.pub.cs.systems.eim.myapplicationf.locconsum_recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.util.Base64Utils;

import de.hdodenhof.circleimageview.CircleImageView;
import ro.pub.cs.systems.eim.myapplicationf.R;

public class LocuriDeConsumViewHolder extends RecyclerView.ViewHolder {

    public TextView adresa;
    public TextView city;
    public TextView postalCode;
    public ImageView eraseButton;

    public LocuriDeConsumViewHolder(@NonNull View itemView) {
        super(itemView);
        adresa =(TextView) itemView.findViewById(R.id.adresa);
        city = (TextView) itemView.findViewById(R.id.oras_locatii);
        postalCode = (TextView) itemView.findViewById(R.id.codpostal_locatii);
        eraseButton = (ImageView) itemView.findViewById(R.id.remove_material_icon);

    }
}
