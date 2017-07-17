package com.bowazik.bob.ibet.network;

import android.os.AsyncTask;
import android.util.Log;

import com.bowazik.bob.ibet.utility.Constants;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by bob on 13.07.17.
 */

public class CreateBetAsyncTask extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = "CreateBetAsyncTask";
    public AsyncInterfaces.CreateBetAsyncInterface createBetAsyncInterface;
    private int error = 0;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... userData) {
        int responseCode = 0;
        String url = Constants.IBET_SERVER_PHP_URL_CREATE_BET, serverResponse,
                data = null, title = userData[0], description = userData[1],
                contender = userData[2], value = userData[3], creator = userData[4];
        HttpURLConnection httpURLConnection = null;
        OutputStreamWriter outputStreamWriter;
        URL server = null;

        try{
            data = URLEncoder.encode("title" , "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
            data += "&" + URLEncoder.encode("description" , "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8");
            data += "&" + URLEncoder.encode("contender" , "UTF-8") + "=" + URLEncoder.encode(contender, "UTF-8");
            data += "&" + URLEncoder.encode("value" , "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            data += "&" + URLEncoder.encode("creator" , "UTF-8") + "=" + URLEncoder.encode(creator, "UTF-8");
            Log.v(TAG, "New bet Data: "+data);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        try{
            server = new URL(url);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

        if(server != null && data != null){
            try{
                httpURLConnection = (HttpURLConnection)server.openConnection();
                httpURLConnection.setDoOutput(true);

                outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(data);
                outputStreamWriter.flush();

                responseCode = httpURLConnection.getResponseCode();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(responseCode == HttpURLConnection.HTTP_OK){
            serverResponse = InputStreamInterpreter.interpretInpuStream(httpURLConnection);
            Log.v(TAG, "Response: " + serverResponse);
        }else{
            serverResponse = InputStreamInterpreter.interpretInpuStream(httpURLConnection);
            Log.v(TAG, "Response error: " + serverResponse);
            error = 1;
        }

        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }

        return true;
    }

    protected void onPostExecute(Boolean result){
        if(error == 0){
            createBetAsyncInterface.onCreateBetSuccess();
        }else{
            createBetAsyncInterface.onCreateBetError();
        }
    }
}
