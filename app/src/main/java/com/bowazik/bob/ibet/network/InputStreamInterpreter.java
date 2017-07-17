package com.bowazik.bob.ibet.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by bob on 13.07.17.
 */

public class InputStreamInterpreter {

    static String interpretInpuStream(HttpURLConnection httpURLConnection){

        BufferedReader reader;
        StringBuffer response = new StringBuffer();
        String line;

        try{
            reader = new BufferedReader(new java.io.InputStreamReader(httpURLConnection.getInputStream()));
            while((line = reader.readLine()) != null){
                response.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return response.toString();
    }
}
