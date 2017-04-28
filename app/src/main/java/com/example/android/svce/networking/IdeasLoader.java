package com.example.android.svce.networking;

import android.content.Context;
import android.util.Log;
import android.content.AsyncTaskLoader;
import com.example.android.svce.model.POJO.Ideas;
import com.example.android.svce.model.POJO.User;
import com.example.android.svce.utils.Constant;
import com.example.android.svce.utils.IdeasURIUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by jennifernghinguyen on 4/5/17.
 */

public class IdeasLoader extends AsyncTaskLoader<ArrayList<Ideas>>  {
   private String host;
    private String sort;
    private Context context;
    private String startIndex;
    private String endIndex;
    private String category;
    private String author;
    public IdeasLoader(Context context, String host, String sort, String category, String author, String startIndex, String endIndex) {
        super(context);
        this.host = host;
        this.sort = sort;
        this.context = context;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.category = category;
        this.author = author;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Ideas> loadInBackground() {
       ArrayList<Ideas> ideas = new ArrayList<>();
        Log.i("loader", "load in the back ground");

        if(host == null || !host.equals(Constant.HOST)){
            return  null;
        }

        String url = IdeasURIUtils.buildUserUrl(context, host, sort, category, author, startIndex, endIndex);
        if(url !=null){
            ideas = IdeasURIUtils.fetchData(url);
        }
    Log.i("loader", url);
        return  ideas;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(ArrayList<Ideas> data) {
        super.onCanceled(data);
        if(data !=null){
            data.clear();
        }
    }
}
