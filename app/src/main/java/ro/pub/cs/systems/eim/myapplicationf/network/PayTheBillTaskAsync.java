package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

public class PayTheBillTaskAsync extends AsyncTask<String,Void,Integer> {

    MainActivity mainActivity;

    public PayTheBillTaskAsync(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String firstDay = strings[0].toString();
        String lastDay = strings[1].toString();
        String value = strings[2].toString();
        String streetAddress = strings[3].toString();
        String city = strings[2].toString();
        String postalCode = strings[3].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut("http://10.0.2.2:5001/api/new/paybills");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("firstDay", firstDay);
            jsonObj.put("lastDay", lastDay);
            jsonObj.put("value", value);
            jsonObj.put("streetAddress", firstDay);
            jsonObj.put("city", lastDay);
            jsonObj.put("postalCode", value);

            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPut.setEntity(entity);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("Authorization", "Bearer " + ((MainActivity)mainActivity).getJwtTokenCode());
            HttpResponse httpResponse = httpClient.execute(httpPut);
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

                builder.setMessage("Factura a fost platita")
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

                builder.setMessage("Factura nu a putut fi platita")
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
