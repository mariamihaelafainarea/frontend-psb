package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

public class AddIndexTaskAsync extends AsyncTask<String,Void, Integer> {

    MainActivity mainActivity;
    public AddIndexTaskAsync(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Integer doInBackground(String... strings) {

        String streetAddress = strings[0].toString();
        Long value = Long.parseLong(strings[1]);
        Long month = Long.parseLong(strings[2]);
        Long year = Long.parseLong(strings[3]);


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/new/index");


            JSONObject jsonObj = new JSONObject();
            jsonObj.put("streetAdress", streetAddress);
            jsonObj.put("value", value);
            jsonObj.put("month", month);
            jsonObj.put("year", year);


            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + ((MainActivity)mainActivity).getJwtTokenCode());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

                builder.setTitle("Succes");

                builder.setMessage("Indexul a fost adaugat")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

                builder.setTitle("Eroare!");

                builder.setMessage("Indexul nu a putut fi adaugat")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        } catch (Exception e ) {
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

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
}
