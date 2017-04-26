package com.example.android.svce.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.svce.R;
import com.example.android.svce.model.POJO.Comment;

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

import static com.example.android.svce.R.string.date;

/**
 * Created by jennifernghinguyen on 4/23/17.
 */

public final class CommentURIUtils {

    static final String LOG_TAG = CommentURIUtils.class.getSimpleName();

    static Context ct;

    /**
     * build valid url from host , username query, password query
     *
     * @param context - UI context
     * @param ideaid    - sort query
     * @return String - urlString
     */


    public static String buildCommentUrl(Context context, String host, String ideaid) {
        ct = context;
        String urlString = null;
        if (host == null) {
            return urlString;
        }

        Uri base = Uri.parse(host);
        Uri.Builder builder = base.buildUpon();
        builder.appendEncodedPath(Constant.COMMENT_PATH + "/");
        builder.appendQueryParameter(Constant.IDEAID_QUERY, ideaid);
        urlString = builder.toString();
        Log.i(LOG_TAG, urlString);
        return urlString;


    }

    public static String buildCommentPostUrl(Context context, String host) {
        ct = context;
        String urlString = null;
        if (host == null) {
            return urlString;
        }

        Uri base = Uri.parse(host);
        Uri.Builder builder = base.buildUpon();
        builder.appendEncodedPath(Constant.COMMENT_PATH + "/");
        urlString = builder.toString();
        Log.i(LOG_TAG, urlString);
        return urlString;


    }

    /**
     * create url from urlString - created by buildUserUrl()
     *
     * @param urlString
     * @return URL object
     */
    private static URL createURL(String urlString) {
        URL url = null;
        if (urlString == null) {
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
     *
     * @return jsoresponse - string
     */
    private static String downloadJsonResponse(URL url) throws IOException {
        String response = "";
        if (url == null) {
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

        } finally {
            //close connection if exist
            if (connection != null) {
                connection.disconnect();
            }
            // close inStream if exist
            if (inputStream != null) {
                inputStream.close();
            }
        }


        return response;
    }

    private static String getResponseFromStream(InputStream inputStream) {
        StringBuilder response = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    response.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                if (inputStreamReader == null) {
                    Log.e(LOG_TAG, ct.getString(R.string.input_stream_reader_null));
                }

                if (reader == null) {
                    Log.e(LOG_TAG, ct.getString(R.string.buffer_reader_null));
                }
            }
        } else {

            Log.e(LOG_TAG, ct.getString(R.string.inputstream_null));
        }
        return response.toString();
    }

    /**
     * extract user info from json response
     */
    private static ArrayList<Comment> extractComment(String response) {
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            JSONArray root = new JSONArray(response);
            for (int i = 0; i < root.length(); i++) {

                JSONObject userObject = (JSONObject) root.get(i);
                int commentid = userObject.getInt("commentid");
                String comment = getString(userObject, "comment");
                String author = getString(userObject, "author");
                int ideaId = userObject.getInt("ideaId");

                if (comment != null && author != null) {
                    comments.add(new Comment(commentid, comment, author, ideaId));

                } else {
                    break;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return comments;
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
            Log.e(LOG_TAG, string + " " + ct.getString(R.string.string_null));
        }

        return str;
    }


    public static ArrayList<Comment> fetchData(String urlString) {
        ArrayList<Comment> comments = new ArrayList<>();
        URL url = createURL(urlString);

        String response = "";

        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!response.equals("")) {
            comments = extractComment(response);
        }

        return comments;
    }

    public static String createJSON(String comment, String _author, int ideaid){
        JSONObject ideaObject = new JSONObject();

        try {
            ideaObject.put(Constant.JSON_COMMENT, comment);
            ideaObject.put(Constant.JSON_AUTHOR, _author);
            ideaObject.put(Constant.JSON_IDEAID, ideaid);
            Log.i("create json", ideaObject.toString());
            return ideaObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
