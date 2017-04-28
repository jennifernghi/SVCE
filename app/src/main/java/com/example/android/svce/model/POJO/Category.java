package com.example.android.svce.model.POJO;

import java.io.Serializable;

/**
 * Created by jennifernghinguyen on 4/26/17.
 */

public class Category implements Serializable{
    private String category;

    public Category(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}
