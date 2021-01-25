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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

public class RegisterAsyncTask extends AsyncTask<String,Void, JSONObject> {
    Activity activity;

    public RegisterAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        String nume = strings[0].toString();
        String prenume = strings[1].toString();
        String email = strings[2].toString();
        String parola = strings[3].toString();
        String telefon = strings[4].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/clients/register");

            List<NameValuePair> parametrii = new ArrayList<NameValuePair>();
            parametrii.add(new BasicNameValuePair("lastname", nume));
            parametrii.add(new BasicNameValuePair("firstName", prenume));
            parametrii.add(new BasicNameValuePair("phoneNumber", telefon));
            parametrii.add(new BasicNameValuePair("email", email));
            parametrii.add(new BasicNameValuePair("password", parola));


            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parametrii, HTTP.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);

            HttpResponse httpPostResponse = httpClient.execute(httpPost);
            HttpEntity httpPostEntity = httpPostResponse.getEntity();
            if (httpPostEntity != null) {
                String result = EntityUtils.toString(httpPostEntity);
                try {
                    return new JSONObject(result);
                }catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }else {
                Log.i("a", "Not found");

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {
            if (jsonObject != null && jsonObject.getString("response").equals("done")) {
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
//                            TODO:
//                            resetFragment();
                        }

                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            e.printStackTrace();
        }
    }




}
