package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareLocuriDeConsumFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.IstoricIndexFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.TransmitereIndexFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.IstoricIndex;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;

public class GetAllLocuriDeConsumTaskAsync extends AsyncTask<String,Void, JSONArray> {

    Activity activity;
    AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment;
    TransmitereIndexFragment transmitereIndexFragment;
    IstoricIndexFragment istoricIndexFragment;
    public GetAllLocuriDeConsumTaskAsync(Activity activity, AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment) {
        this.activity = activity;
        this.administrareLocuriDeConsumFragment = administrareLocuriDeConsumFragment;
    }

    public GetAllLocuriDeConsumTaskAsync(Activity activity, TransmitereIndexFragment transmitereIndexFragment) {
        this.activity = activity;
        this.transmitereIndexFragment = transmitereIndexFragment;
    }

    public GetAllLocuriDeConsumTaskAsync(Activity activity, IstoricIndexFragment istoricIndexFragment) {
        this.activity = activity;
        this.istoricIndexFragment = istoricIndexFragment;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        String jwtToken = strings[0].toString();


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://10.0.2.2:5001/api/new/locations");

            httpGet.setHeader("Authorization","Bearer "+ jwtToken);
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpGetEntity = httpResponse.getEntity();
            if (httpGetEntity != null) {
                String result = EntityUtils.toString(httpGetEntity);
                try {
                    return new JSONArray(result);
                }catch (Exception e) {
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
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);

        try {
            if(jsonArray != null) {
                if (jsonArray.length() > 0) {

                    if(administrareLocuriDeConsumFragment !=null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject j = jsonArray.getJSONObject(i);
                            LocConsum q = new LocConsum();
                            q.setAddress(j.getString("streetAddress"));
                            q.setCity(j.getString("city"));
                            q.setPostalcode(j.getString("postalCode"));
                            System.err.println("AICI");
                            System.out.println(q.toString());
                            administrareLocuriDeConsumFragment.getDataset().add(q);
                            administrareLocuriDeConsumFragment.getLocConsumAdapter().notifyDataSetChanged();

                        }
                    }else if(istoricIndexFragment !=null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject j = jsonArray.getJSONObject(i);
                            IstoricIndex q = new IstoricIndex();
                            q.setValue(j.getInt("value"));
                            q.setMonth(j.getInt("month"));
                            q.setYear(j.getInt("year"));
                            istoricIndexFragment.getDataset().add(q);
                            istoricIndexFragment.getIstoricIndexAdapter().notifyDataSetChanged();

                        }
                    }else if(transmitereIndexFragment != null) {

                        Spinner s  = transmitereIndexFragment.getAddressSpinner();


                        ArrayList<String> years = new ArrayList<String>();
                        for (int i =0; i < jsonArray.length(); i++) {
                            JSONObject j = jsonArray.getJSONObject(i);
                            String nou = j.getString("streetAddress") + " "+ j.getString("city")+ " "+j.getString("postalCode");
                            years.add(nou);

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>((MainActivity)activity, android.R.layout.simple_spinner_item, years);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        s.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                        s.setSelection(0);
                        s.setBackgroundColor(Color.parseColor("#a9a9a9"));
                        ArrayList<String> array = new ArrayList<>();

                        for (int i =0; i < jsonArray.length(); i++) {
                            JSONObject j = jsonArray.getJSONObject(i);
                           array.add(j.getString("streetAddress"));
                        }

                        transmitereIndexFragment.setDataset(array);


                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
