package ro.pub.cs.systems.eim.myapplicationf.network;

import android.os.AsyncTask;

import ro.pub.cs.systems.eim.myapplicationf.MainActivity;

public class EraseLocDeConsumTaskAsync extends AsyncTask<String,Void,String> {

    MainActivity mainActivity;
    public EraseLocDeConsumTaskAsync(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
