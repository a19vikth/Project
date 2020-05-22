package com.example.project;

public class planeter {
    private String mimageurl;
    private String mnamn;
    public planeter (String imageurl, String namn){
        mimageurl = imageurl;
        mnamn = namn;
    }

    public String getimageurl() {
        return mimageurl;
    }

    public String getMnamn() {
        return mnamn;
    }
}
