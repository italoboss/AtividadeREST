package com.example.italo.atividaderest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by italo_boss on 29/06/2016.
 */
public class DownloaderTask extends AsyncTask<String, Void, CEPObject> {
    private Gson gson;
    private ResultActivity resultActivity;

    public DownloaderTask(ResultActivity resultActivity) {
        this.resultActivity = resultActivity;
    }

    @Override
    protected CEPObject doInBackground(String... params) {
        String cep = params[0];
        ConnectivityManager connManager = (ConnectivityManager) resultActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String result = informacoesCEP(cep);
            gson = new Gson();
            return gson.fromJson(result, CEPObject.class);
        } else {
            return null;
        }
    }

    private String informacoesCEP(String cep) {
        URL url = null;
        try {
            url = new URL("http://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String content = "";
            while ((data = reader.readLine()) != null) {
                content += data + "\n";
            }
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(CEPObject cepObj) {
        resultActivity.setValuesOfCEP(cepObj);
    }

}
