package ro.pub.cs.systems.eim.myapplicationf.network;

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
import ro.pub.cs.systems.eim.myapplicationf.fragments.DateDeContactFragment;
import ro.pub.cs.systems.eim.myapplicationf.models.LocConsum;

public class GetDateDeContactTaskAsync extends AsyncTask<String,Void, JSONObject> {

    MainActivity mainActivity;
    DateDeContactFragment dateDeContactFragment;

    public GetDateDeContactTaskAsync(MainActivity mainActivity, DateDeContactFragment dateDeContactFragment) {
        this.mainActivity = mainActivity;
        this.dateDeContactFragment = dateDeContactFragment;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String jwtToken = strings[0].toString();


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpGet = new HttpPost("http://10.0.2.2:5001/api/clients/informations");
            System.err.println(jwtToken);

            httpGet.setHeader("Authorization","Bearer "+ mainActivity.getJwtTokenCode());
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpGetEntity = httpResponse.getEntity();
            if (httpGetEntity != null) {
                String result = EntityUtils.toString(httpGetEntity);
                try {
                    return new JSONObject(result);
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
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);


        try {
            if(jsonObject != null) {
                dateDeContactFragment.getNume().setText(jsonObject.get("firstName").toString());
                dateDeContactFragment.getPrenume().setText(jsonObject.get("lastName").toString());
                dateDeContactFragment.getEmail().setText(jsonObject.get("email").toString());
                dateDeContactFragment.getTelefon().setText(jsonObject.get("phoneNumber").toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
