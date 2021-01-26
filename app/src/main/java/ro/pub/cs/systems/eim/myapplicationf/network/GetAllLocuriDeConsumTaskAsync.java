package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
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

import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareLocuriDeConsumFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;

public class GetAllLocuriDeConsumTaskAsync extends AsyncTask<String,Void, JSONArray> {

    Activity activity;
    AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment;
    public GetAllLocuriDeConsumTaskAsync(Activity activity, AdministrareLocuriDeConsumFragment administrareLocuriDeConsumFragment) {
        this.activity = activity;
        this.administrareLocuriDeConsumFragment = administrareLocuriDeConsumFragment;
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
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
