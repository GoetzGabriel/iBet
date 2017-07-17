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

public class SignupAsyncTask extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = "SignupAsyncTask";
    public AsyncInterfaces.SignupAsyncInterface signupAsyncInterface;
    private int error = 0;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... userData) {
        int responseCode = 0;
        String url = Constants.IBET_SERVER_PHP_URL_CREATE_USER, serverResponse, data = null, username = userData[0], password = userData[1];
        HttpURLConnection httpURLConnection = null;
        OutputStreamWriter outputStreamWriter;
        URL server = null;

        try{
            data = URLEncoder.encode("username" , "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password" , "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            Log.v(TAG, "Signup Data: "+data);
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
            signupAsyncInterface.onSignupSuccess();
        }else{
            signupAsyncInterface.onSignupError();
        }
    }
}
