package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.LoginActivity;
import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.RegisterActivity;

public class RegisterAsyncTask extends AsyncTask<String,Void, Integer> {
    Activity activity;

    public RegisterAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Integer doInBackground(String... strings) {

        String nume = strings[0].toString();
        String prenume = strings[1].toString();
        String email = strings[2].toString();
        String parola = strings[3].toString();
        String telefon = strings[4].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/clients/register");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("firstName", prenume);
            jsonObj.put("lastname", nume);
            jsonObj.put("phoneNumber", telefon);
            jsonObj.put("email", email);
            jsonObj.put("password", parola);

            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            return httpResponse.getStatusLine().getStatusCode();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Integer code) {
        super.onPostExecute(code);
        activity.finish();
        try {
            if (code != null && code == 200) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setTitle("Contul a fost creat !");

                builder.setMessage("Intra pe mail si confirma adresa!")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                activity.finish();
                            }

                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setTitle("A aparut o eroare !");

                builder.setMessage("Ne pare rau, a aparut o eroare. Reincearca!")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                resetFragment();
                            }

                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        }catch (Exception e ){
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("A aparut o eroare !");

            builder.setMessage("Ne pare rau, a aparut o eroare. Reincearca!")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            resetFragment();
                        }

                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            e.printStackTrace();
        }
    }


    public void resetFragment() {
        RegisterActivity registerActivity = (RegisterActivity) this.activity;
        registerActivity.getEmailEditText().setText("");
        registerActivity.getNumeEditText().setText("");
        registerActivity.getPrenumeEditText().setText("");
        registerActivity.getTelefonEditText().setText("");
        registerActivity.getParolaEditText().setText("");

    }




}
