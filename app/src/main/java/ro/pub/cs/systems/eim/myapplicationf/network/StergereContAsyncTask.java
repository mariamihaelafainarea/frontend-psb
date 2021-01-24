package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

public class StergereContAsyncTask extends AsyncTask<String,Void, JSONObject> {

    Activity activity;
    public StergereContAsyncTask(MainActivity mainActivity) {
        activity = mainActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
