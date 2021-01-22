package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import android.view.WindowManager;

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

public class LoginTaskAsync extends AsyncTask<String, Void, JSONObject> {
    Activity activity;
    public LoginTaskAsync(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected JSONObject doInBackground(String... params) {

        String username = params[0].toString();
        String password = params[1].toString();

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/users/getUser");

            List<NameValuePair> parametrii = new ArrayList<NameValuePair>();
            parametrii.add(new BasicNameValuePair("username", username));
            parametrii.add(new BasicNameValuePair("password", password));

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


    }

}
