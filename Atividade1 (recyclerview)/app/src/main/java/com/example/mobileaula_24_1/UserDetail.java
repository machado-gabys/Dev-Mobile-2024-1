package com.example.mobileaula_24_1;

public class UserDetail {
    private String name;
    private String post;
    private String comment;

    public UserDetail(String name, String post, String comment) {
        this.name = name;
        this.post = post;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
