package com.example.android.svce.model.POJO;

/**
 * Created by jennifernghinguyen on 4/23/17.
 */

public class Comment {
    private int commentId;
    private String comment;
    private String author;
    private int ideaId;

    public Comment(int commentId, String comment, String author, int ideaId){
        this.commentId = commentId;
        this.comment= comment;
        this.author = author;
        this.ideaId = ideaId;
    }

    public int getCommentId(){
        return this.commentId;
    }

    public String getComment(){
        return this.comment;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getIdeaId(){
        return this.ideaId;
    }
}
