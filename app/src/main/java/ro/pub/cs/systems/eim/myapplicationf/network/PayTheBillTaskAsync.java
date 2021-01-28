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

import java.nio.file.attribute.FileAttribute;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareFacturiNeplatiteFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;

public class PayTheBillTaskAsync extends AsyncTask<String,Void,Integer> {

    MainActivity mainActivity;
    AdministrareFacturiNeplatiteFragment facturiNeplatite;

    public PayTheBillTaskAsync(MainActivity mainActivity, AdministrareFacturiNeplatiteFragment facturiNeplatite) {
        this.mainActivity = mainActivity;
        this.facturiNeplatite = facturiNeplatite;
    }
    public String copyI;
    @Override
    protected Integer doInBackground(String... strings) {
        String firstDay = strings[0].toString();
        String lastDay = strings[1].toString();
        String value = strings[2].toString();
        String streetAddress = strings[3].toString();
        String city = strings[4].toString();
        String postalCode = strings[5].toString();
        copyI = strings[6].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPut httpPut = new HttpPut("http://10.0.2.2:5001/api/new/bill/paybills");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("firstDay", firstDay);
            jsonObj.put("lastDay", lastDay);
            jsonObj.put("value", value);
            jsonObj.put("streetAddress", streetAddress);
            jsonObj.put("city", city);
            jsonObj.put("postalCode", postalCode);

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

                             //   facturiNeplatite.getDataset().remove(Integer.valueOf(copyI));
                                facturiNeplatite.getDataset().clear();
                                facturiNeplatite.getFacturiNeplatiteAdaptor().notifyAll();
                                facturiNeplatite.getFacturiNeplatiteAdaptor().notifyItemRangeChanged(0,0);
                             //   facturiNeplatite.getFacturiNeplatiteAdaptor().notifyItemRemoved(Integer.valueOf(copyI));
                              //  facturiNeplatite.getFacturiNeplatiteAdaptor().notifyItemRangeChanged(Integer.valueOf(copyI),facturiNeplatite.getDataset().size());
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
