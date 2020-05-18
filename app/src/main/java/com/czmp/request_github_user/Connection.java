package com.czmp.request_github_user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    public static String getJsonFromApi(String url){
        String back = "";
        try {
            URL apiEnd = new URL(url);
            HttpURLConnection con;


            con = (HttpURLConnection) apiEnd.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(150000);
            con.setConnectTimeout(150000);
            con.connect();

            int cdResponse = con.getResponseCode();
            InputStream is;
            if(cdResponse == HttpURLConnection.HTTP_OK){
                is = con.getInputStream();
            }else{
                is = con.getErrorStream();
            }

            back = converterInputStreamToString(is);
            is.close();
            con.disconnect();
        } catch (Exception e){
            e.printStackTrace();
        }
        return back;
    }

    private static String converterInputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String line;

            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine())!=null){
                buffer.append(line);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }
}