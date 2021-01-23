package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentManager;

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

public class TrasnmitereIndexAsyncTask extends AsyncTask<String,Void, JSONObject> {

    Activity activity;
    public TrasnmitereIndexAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String adresa = params[0].toString();
        String luna = params[1].toString();
        String an = params[2].toString();
        String index = params[3].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/indices");

            List<NameValuePair> parametrii = new ArrayList<NameValuePair>();
            parametrii.add(new BasicNameValuePair("adresa", adresa) );
            parametrii.add(new BasicNameValuePair("luna", luna));
            parametrii.add(new BasicNameValuePair("an", an));
            parametrii.add(new BasicNameValuePair("index", index));

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
        FragmentManager fm = ((MainActivity) activity).getSupportFragmentManager();
        fm.popBackStack ("trasnmitereindex", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fm.popBackStack ("index", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        //return to main


    }
}
