package ro.pub.cs.systems.eim.myapplicationf.network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.AdministrareFacturiNeplatiteFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.FacturiNeplatite;
import ro.pub.cs.systems.eim.myapplicationf.models.IstoricIndex;

public class GetAllFacturiNeplatiteAsyncTask extends AsyncTask<String,Void, JSONArray> {

    AdministrareFacturiNeplatiteFragment administrareFacturiNeplatiteFragment = new AdministrareFacturiNeplatiteFragment();
    MainActivity mainActivity;
    public GetAllFacturiNeplatiteAsyncTask(MainActivity mainActivity, AdministrareFacturiNeplatiteFragment administrareFacturiNeplatiteFragment) {
        this.mainActivity = mainActivity;
        this.administrareFacturiNeplatiteFragment = administrareFacturiNeplatiteFragment;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        String streetAddress = strings[0].toString();
        String city = strings[1];
        String postalCode = strings[2];


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/new/bill");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("streetAddress", streetAddress);
            jsonObj.put("city", city);
            jsonObj.put("postalCode", postalCode);
            jsonObj.put("paid","false");
            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization","Bearer "+ ((MainActivity)mainActivity).getJwtTokenCode());
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpPost);
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
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject j = jsonArray.getJSONObject(i);
                        FacturiNeplatite q = new FacturiNeplatite();
                        q.setFirstDay(j.getString("firstDay"));
                        q.setLastDay(j.getString("lastDay"));
                        q.setValue(j.getInt("value"));
                        q.setPaid(j.getBoolean("paid"));
                        administrareFacturiNeplatiteFragment.getDataset().add(q);
                        administrareFacturiNeplatiteFragment.getFacturiNeplatiteAdaptor().notifyDataSetChanged();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
