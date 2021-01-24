package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONArray;

public class GetAllIstoricIndexesAsyncTask extends AsyncTask<String,Void, JSONArray> {

    Activity activity;
    public GetAllIstoricIndexesAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
    }
}
