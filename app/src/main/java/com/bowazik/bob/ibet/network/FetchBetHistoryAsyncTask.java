package com.bowazik.bob.ibet.network;

import android.os.AsyncTask;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.utility.Constants;
import com.bowazik.bob.ibet.utility.IbetUtility;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 13.07.17.
 */

public class FetchBetHistoryAsyncTask extends AsyncTask<Integer, Void, Boolean> {

    private static final String TAG = "FetchBetListAsyncTask";
    public AsyncInterfaces.FetchBetHistoryAsyncInterface fetchBetHistoryAsyncInterface;
    private List<iBet> betHistory = new ArrayList<>();
    private int error = 0;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Integer... ids) {
        int userId = ids[0], responseCode = 0;
        String url = Constants.IBET_SERVER_PHP_URL_BETS_BY_ID, serverResponse, data = null;
        HttpURLConnection httpURLConnection = null;
        OutputStreamWriter outputStreamWriter = null;
        JSONArray jsonArray = null;
        URL server = null;

        try{
            data = URLEncoder.encode("userId" , "UTF-8") + "=" + URLEncoder.encode(String.valueOf(userId), "UTF-8");
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

            try{
                jsonArray = new JSONArray(serverResponse);
            }catch(JSONException e){
                e.printStackTrace();
            }

            if(jsonArray != null){
                String[] requestedStatus = {Constants.IBET_STATUS_WON, Constants.IBET_STATUS_LOST};
                betHistory = IbetUtility.jsonArrayToIbetList(jsonArray, requestedStatus);
            }
        }else{
            error = 1;
        }

        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        return true;
    }

    protected void onPostExecute(Boolean result){
        if(error == 0){
            fetchBetHistoryAsyncInterface.onFetchBetHistorySuccess(betHistory);
        }else{
            fetchBetHistoryAsyncInterface.onFetchBetHistoryError("Hardcoded error. History fetch error.");
        }
    }
}
