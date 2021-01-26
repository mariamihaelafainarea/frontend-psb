package ro.pub.cs.systems.eim.myapplicationf.network;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONObject;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;
import ro.pub.cs.systems.eim.myapplicationf.fragments.DateDeContactFragment;

public class StergereContAsyncTask extends AsyncTask<String,Void, JSONObject> {

    MainActivity mainActivity;
    DateDeContactFragment dateDeContactFragment;

    Activity activity;
    public StergereContAsyncTask(MainActivity mainActivity,DateDeContactFragment dateDeContactFragment) {
        activity = mainActivity;
        this.dateDeContactFragment = dateDeContactFragment;
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
