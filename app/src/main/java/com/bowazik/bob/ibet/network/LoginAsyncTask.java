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
 * Async task to send user data to the web server that validates the data.
 * If the web server sends a valid response the according model callback operations is called.
 */

public class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = "LoginAsyncTask";
    public AsyncInterfaces.LoginAsyncInterface loginAsyncInterface;
    private int error = 0, userId = -1;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... userData) {
        int responseCode = 0;
        String url = Constants.IBET_SERVER_PHP_URL_CHECK_USER, serverResponse, data = null, username = userData[0], password = userData[1];
        HttpURLConnection httpURLConnection = null;
        OutputStreamWriter outputStreamWriter;
        URL server = null;

        try{
            data = URLEncoder.encode("username" , "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password" , "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            Log.v(TAG, "Login Data: "+data);
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
            userId = Integer.parseInt(serverResponse);
            if(userId == -1){
                error = 1;
            }
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
            loginAsyncInterface.onLoginSuccess(userId);
        }else{
            loginAsyncInterface.onLoginError();
        }
    }
}
