package com.example.firebase_login;
//데이터베이스와 연동하기위해 class 로 만들어둠
public class Went {
    private String place;
    private String selection;
    private String picture;
    private int rating;

    public Went(){}

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPlace() {
        return place;
    }

    public int getRating() {
        return rating;
    }
}
