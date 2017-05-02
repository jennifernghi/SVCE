package com.example.android.svce.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.utils.CommentURIUtils;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.IdeasURIUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by jennifernghinguyen on 4/25/17.
 */

public class CommentPost extends AsyncTaskLoader<String> {
    private Context context;
    private String comment;
    private int ideaid;
    private String author;


    public CommentPost(Context context, String comment, String author, int ideaid){
        super(context);
        this.context = context;
        this.comment = comment;
        this.ideaid = ideaid;
        this.author = author;
    }
    @Override
    public String loadInBackground() {
        String response="";
        URL url = null;
        try {
             url = new URL(CommentURIUtils.buildCommentPostUrl(context, Constant.HOST));
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
                wr.writeBytes(CommentURIUtils.createJSON(comment, author, ideaid));
                wr.flush();
                wr.close();
                Log.i("comment post",String.valueOf(connection.getResponseCode()));
                return String.valueOf(connection.getResponseCode());

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
