package com.example.android.svce.model.POJO;

import java.io.Serializable;

/**
 * Created by jennifernghinguyen on 2/26/17.
 */

public class Ideas implements Serializable {
    private int ideaId;
    private String title;
    private String content;
    private String date;
    private String category;
    private int likes;
    private String author;

    public Ideas(int ideaId,
                 String title,
                 String content,
                 String date,
                 String category,
                 int likes,
                 String author
                ){
        this.ideaId = ideaId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
        this.likes = likes;
        this.author = author;
    }
    public int getIdeaId(){return this.ideaId;}
    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getDate(){
        return this.date;
    }
    public int getLikes(){
        return this.likes;
    }

    public String getContent(){
        return this.content;
    }

    public String getCategory(){
        return this.category;
    }
}
