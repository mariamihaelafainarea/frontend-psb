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

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.IstoricIndexFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.IstoricIndex;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;

public class GetAllIstoricIndexesAsyncTask extends AsyncTask<String,Void, JSONArray> {

    Activity activity;
    IstoricIndexFragment istoricIndexFragment;
    public GetAllIstoricIndexesAsyncTask(Activity activity, IstoricIndexFragment istoricIndexFragment) {
        this.activity = activity;
        this.istoricIndexFragment = istoricIndexFragment;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        String streetAddress = strings[0].toString();
        String city = strings[1];
        String postalCode = strings[2];


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/new/istoric");



            JSONObject jsonObj = new JSONObject();
            jsonObj.put("streetAddress", streetAddress);
            jsonObj.put("city", city);
            jsonObj.put("postalCode", postalCode);
            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization","Bearer "+ ((MainActivity)activity).getJwtTokenCode());
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
                        IstoricIndex q = new IstoricIndex();
                        q.setValue(j.getInt("value"));
                        q.setMonth(j.getInt("month"));
                        q.setYear(j.getInt("year"));
                        istoricIndexFragment.getDataset().add(q);
                        istoricIndexFragment.getIstoricIndexAdapter().notifyDataSetChanged();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
