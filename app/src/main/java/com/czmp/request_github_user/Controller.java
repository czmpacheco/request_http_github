package com.czmp.request_github_user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {


    public GitHub getInfo(String end){
        String json = Connection.getJsonFromApi(end);
        GitHub back = parseJson(json);
        return back;
    }

    private GitHub parseJson(String json){
        try {
            GitHub user = new GitHub();

            JSONObject jsonObj = new JSONObject(json);

            user.setName(jsonObj.getString("name"));
            user.setUsername(jsonObj.getString("login"));
            user.setLocation(jsonObj.getString("location"));
            user.setCompany(jsonObj.getString("company"));
            user.setEmail(jsonObj.getString("email"));
            user.setBio(jsonObj.getString("bio"));
            user.setImage(loadImg(jsonObj.getString("avatar_url")));

            return user;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap loadImg(String url) {
        try {
            URL link = new URL(url);
            InputStream inputStream = link.openStream();
            Bitmap img = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
