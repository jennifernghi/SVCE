package com.example.android.svce.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.svce.R;
import com.example.android.svce.model.POJO.Ideas;
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
import java.util.ArrayList;

import static com.example.android.svce.R.drawable.phone;

/**
 * Created by jennifernghinguyen on 4/4/17.
 */

public final class IdeasURIUtils {
     static final String LOG_TAG = IdeasURIUtils.class.getSimpleName();

     static Context ct;

    /**
     * build valid url from host , username query, password query
     * @param context - UI context
     * @param sort - sort query
     * @return String - urlString
     *
     */


    public static String buildUserUrl(Context context, String host, String sort, String startIndex, String endIndex){
        ct=context;
        String urlString = null;
        if(host == null){
            return urlString;
        }

        Uri base = Uri.parse(host);
        Uri.Builder builder = base.buildUpon();
        builder.appendEncodedPath(Constant.IDEA_PATH +"/");
        builder.appendQueryParameter(Constant.SORT_QUERY, sort);
        if(!startIndex.equals("")&& (!endIndex.equals(""))) {
            builder.appendQueryParameter(Constant.START_INDEX_QUERY, startIndex);
            builder.appendQueryParameter(Constant.END_INDEX_QUERY, endIndex);
        }
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
    private static ArrayList<Ideas> extractIdea(String response){
        ArrayList<Ideas> ideas = new ArrayList<>();

        try {
            JSONArray root = new JSONArray(response);
            for(int i =0; i<root.length(); i++) {
                if(i<=10) {
                    JSONObject userObject = (JSONObject) root.get(i);
                    int ideasId = userObject.getInt("ideaId");
                    String title = getString(userObject, "title");
                    String content = getString(userObject, "content");
                    String date = getString(userObject, "date");
                    String category = getString(userObject, "category");
                    int likes = userObject.getInt("likes");
                    String author = getString(userObject, "author");

                    if (title != null && content != null && date != null && author != null && category != null) {
                        ideas.add(new Ideas(ideasId, title, content, date, category, likes, author));
                    }
                }else{
                    break;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ideas;
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


    public static ArrayList<Ideas> fetchData(String urlString){
        ArrayList<Ideas> ideas = new ArrayList<>();
        URL url = createURL(urlString);

        String response = "";

        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!response.equals("")){
            ideas = extractIdea(response);
        }

        return ideas;
    }

}
