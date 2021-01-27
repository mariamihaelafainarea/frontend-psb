package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.RegisterActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdaugareLocConsumFragment;

public class AddLocDeConsumTaskAsync extends AsyncTask<String, Void, Integer> {
    Activity activity;
    AdaugareLocConsumFragment adaugareLocConsumFragment;
    public AddLocDeConsumTaskAsync(Activity activity,AdaugareLocConsumFragment adaugareLocConsumFragment) {
        this.activity = activity;
        this.adaugareLocConsumFragment = adaugareLocConsumFragment;
    }

    @Override
    protected Integer doInBackground(String... params) {
        String streetAddress = params[0].toString();
        String postalCode = params[1].toString();
        String city = params[2].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/new/locations");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("streetAddress", streetAddress);
            jsonObj.put("postalCode", postalCode);
            jsonObj.put("city", city);

            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + ((MainActivity)activity).getJwtTokenCode());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return httpResponse.getStatusLine().getStatusCode();
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Integer code) {
        super.onPostExecute(code);
        try {
            if (code != null && code == 200) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setTitle("Succes");

                builder.setMessage("Locul de consum a fost adaugat")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                resetFragment();
                                dialog.cancel();

                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setTitle("Eroare!");

                builder.setMessage("Locul de consum nu a putut fi adaugat")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        } catch (Exception e ){
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Eroare");

            builder.setMessage("Oups, a aparut o eroare")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            e.printStackTrace();
        }
    }

    public void resetFragment() {
        adaugareLocConsumFragment.getAdresaEditText().setText("");
        adaugareLocConsumFragment.getCodPostalEditText().setText("");
        adaugareLocConsumFragment.getOrasEditText().setText("");
    }
}

