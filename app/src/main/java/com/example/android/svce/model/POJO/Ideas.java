package com.example.android.svce.model.POJO;

/**
 * Created by jennifernghinguyen on 2/26/17.
 */

public class Ideas {
    private String title;
    private String author;
    private String date;
    private int vote;
    private String content;
    private String category;

    public Ideas(String title,
                 String author,
                 String date,
                 int vote,
                 String content,
                 String category){
        this.title = title;
        this.author = author;
        this.date = date;
        this.vote = vote;
        this.content = content;
        this.category = category;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getDate(){
        return this.date;
    }
    public int getVote(){
        return this.vote;
    }

    public String getContent(){
        return this.content;
    }

    public String getCategory(){
        return this.category;
    }
}
