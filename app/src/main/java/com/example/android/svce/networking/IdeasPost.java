package com.example.android.svce.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.IdeasURIUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 4/25/17.
 */

public class IdeasPost extends AsyncTaskLoader<String> {
    private Context context;
    private String title;
    private String content;
    private String date;
    private String category;
    private String likes;
    private String author;


    public IdeasPost(Context context, String title, String content, String date, String category, String likes, String author){
        super(context);
        this.context = context;
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
        this.likes = likes;
        this.author = author;
    }
    @Override
    public String loadInBackground() {
        String response="";
        URL url = null;
        try {
             url = new URL(IdeasURIUtils.builIdeaPostUrl(context, Constant.HOST) +"/");
            Log.i("post url", url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(url!=null) {
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(IdeasURIUtils.createJSON(title, content, date, category, likes, author));
                wr.flush();
                wr.close();

                return connection.getResponseMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public void onCanceled(String data) {
        super.onCanceled(data);
        if(!data.equals("")){
            data="";
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
