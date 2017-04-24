package com.example.android.svce.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.svce.model.POJO.Comment;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.utils.CommentURIUtils;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.IdeasURIUtils;

import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 4/5/17.
 */

public class CommentLoader extends AsyncTaskLoader<ArrayList<Comment>>  {
   private String host;
    private Context context;
    private String ideaid;

    public CommentLoader(Context context, String host, String ideaid) {
        super(context);
        this.host = host;
        this.context = context;
        this.ideaid = ideaid;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Comment> loadInBackground() {
       ArrayList<Comment> comments = new ArrayList<>();


        if(host == null || !host.equals(Constant.HOST)){
            return  null;
        }

        String url = CommentURIUtils.buildCommentUrl(context, host, ideaid);
        if(url !=null){
            comments = CommentURIUtils.fetchData(url);
        }
    Log.i("loader", url);
        return  comments;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(ArrayList<Comment> data) {
        super.onCanceled(data);
        if(data !=null){
            data.clear();
        }
    }
}
