package com.example.android.svce.model.viewModel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.svce.activities.Login;

import com.example.android.svce.databinding.ActivityMyInfoBinding;
import com.example.android.svce.model.POJO.User;

/**
 * Created by jennifernghinguyen on 4/14/17.
 */

public class MyInfoModel {
    private User user;
    private Context context;
   // private ActivityMyInfoBinding binding;


    public MyInfoModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public String getUserUsername() {
        return this.user.getUsername();
    }

    public String getUserEmail() {
        return this.user.getEmail();
    }

    public String getUserThumbnail() {
        return this.user.getUserThumbnail();
    }

    /**
     * for each image view use Glide to load image to image view with tag app:thumbnailImageUrl
     *
     * @param imageView
     * @param thumbnailImageUrl
     */
    @BindingAdapter("thumbnailImageUrl")
    public static void setImages(ImageView imageView, String thumbnailImageUrl) {
        Context context = imageView.getContext();
        Glide.with(context).load(thumbnailImageUrl).into(imageView);
    }




}
