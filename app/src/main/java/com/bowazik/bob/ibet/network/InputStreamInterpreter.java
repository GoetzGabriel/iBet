package com.bowazik.bob.ibet.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 *  Class to read an inputStream and return it as a String
 */

class InputStreamInterpreter {

    static String interpretInpuStream(HttpURLConnection httpURLConnection){

        BufferedReader reader;
        StringBuilder response = new StringBuilder();
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
