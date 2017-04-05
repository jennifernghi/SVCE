package com.example.android.svce.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.svce.R;
import com.example.android.svce.model.POJO.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by jennifernghinguyen on 4/4/17.
 */

public final class UserURIUtils {
     static final String LOG_TAG = UserURIUtils.class.getSimpleName();
     static final String APIKEY= "77JGJS73JZ9EU3B1JEJC";
     static Context ct;

    /**
     * build valid url from host , username query, password query
     * @param context - UI context
     * @param username - username query
     * @return String - urlString
     *
     */
    public static String buildUserUrl(Context context, String host, String username){
        ct=context;
        String urlString = null;
        if(host == null){
            return urlString;
        }

        Uri base = Uri.parse(host);
        Uri.Builder builder = base.buildUpon();
        builder.appendEncodedPath(Constant.USERPATH +"/");
        builder.appendQueryParameter(Constant.KEY_QUERY, APIKEY);
        builder.appendQueryParameter(Constant.USERNAME_QUERY, username);
        urlString = builder.toString();
        Log.i(LOG_TAG, urlString);
        return urlString;


    }

    /**
     * create url from urlString - created by buildUserUrl()
     * @param urlString
     * @return URL object
     */
    private static URL createURL(String urlString){
        URL url = null;
        if(urlString == null){
            return null;
        }

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, ct.getString(R.string.url_null));
        }

        return url;
    }

    /**
     * download Jsonresponse
     * @return jsoresponse - string
     */
    private static String downloadJsonResponse(URL url) throws IOException{
        String response = "";
        if(url == null){
            return response;
        }

        //open HTTP connection
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream(); //inputStream IOException handled in getResponseFromStream
                response = getResponseFromStream(inputStream);
            }

        }finally {
            //close connection if exist
            if(connection != null){
                connection.disconnect();
            }
            // close inStream if exist
            if (inputStream !=null){
                inputStream.close();
            }
        }


        return response;
    }

    private static String getResponseFromStream(InputStream inputStream) {
        StringBuilder response = new StringBuilder();
        if(inputStream !=null){
            InputStreamReader inputStreamReader= null;
            BufferedReader reader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line!=null){
                    response.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                if(inputStreamReader == null) {
                    Log.e(LOG_TAG, ct.getString(R.string.input_stream_reader_null));
                }

                if(reader == null) {
                    Log.e(LOG_TAG, ct.getString(R.string.buffer_reader_null));
                }
            }
        }else{

            Log.e(LOG_TAG, ct.getString(R.string.inputstream_null));
        }
        return response.toString();
    }
    /**
     * extract user info from json response
     */
    private static User extractUser(String response){
        User user = null;

        try {
            JSONArray root = new JSONArray(response);
            if(root.length()==1){
                JSONObject userObject = (JSONObject) root.get(0);
                String userame = getString(userObject, "username");
                String password = getString(userObject, "password");
                String email = getString(userObject, "email");
                String phone = getString(userObject, "phone");

                if(userame!=null && password!=null && email!=null&&phone!=null){
                    user = new User(userame,email,phone,password);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * get string from Json type string
     *
     * @param object - Json object
     * @param string - string inside Json object
     * @return string
     */
    private static String getString(JSONObject object, String string) {
        String str = null;

        try {
            str = object.getString(string);
        } catch (JSONException e) {
            Log.e(LOG_TAG, string +" " +ct.getString(R.string.string_null));
        }

        return str;
    }

    public static User fetchData(String urlString){
        User user = null;
        URL url = createURL(urlString);

        String response = "";

        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!response.equals("")){
            user = extractUser(response);
        }

        return user;
    }
}
