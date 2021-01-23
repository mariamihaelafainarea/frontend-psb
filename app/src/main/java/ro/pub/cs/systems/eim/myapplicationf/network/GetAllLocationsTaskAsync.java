package ro.pub.cs.systems.eim.myapplicationf.network;

import android.os.AsyncTask;
import android.util.Log;

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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllLocationsTaskAsync extends AsyncTask<String,Void, JSONArray>{

    @Override
    protected JSONArray doInBackground(String... params) {
        String username = params[0].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/locations");

            List<NameValuePair> parametrii = new ArrayList<NameValuePair>();
            parametrii.add(new BasicNameValuePair("adresa", username));

            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parametrii, HTTP.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);

            HttpResponse httpPostResponse = httpClient.execute(httpPost);
            HttpEntity httpPostEntity = httpPostResponse.getEntity();
            if (httpPostEntity != null) {
                String result = EntityUtils.toString(httpPostEntity);
                try {
                    return new JSONArray(result);
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
    protected void onPostExecute(JSONArray o) {
        super.onPostExecute(o);
    }


}
