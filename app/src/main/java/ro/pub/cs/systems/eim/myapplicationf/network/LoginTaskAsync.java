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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.eim.myapplicationf.LoginActivity;
import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

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
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5001/api/clients/login");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("email", username);
            jsonObj.put("password", password);

            StringEntity entity = new StringEntity(jsonObj.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpPostEntity = httpResponse.getEntity();
            if (httpPostEntity != null) {
                String result = EntityUtils.toString(httpPostEntity);
                try {
                    return new JSONObject(result);
                }catch (Exception e) {
                    return null;
                }
            } else {
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
        ((LoginActivity)activity).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if(jsonObject != null) {
            try {
                Intent i = new Intent(activity, MainActivity.class);
                i.putExtra("email", jsonObject.getString("email"));
                activity.startActivity(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Contul nu exista !");

            builder.setMessage("Datele de conectare nu sunt cele asteptate. Te rog sa reincerci!")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }



    }

}
