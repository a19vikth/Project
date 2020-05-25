package com.example.project;

public class planeter {
    private String mimageurl;
    private String mnamn;
    private String mcompany;
    private int mlocation;
    private String mcategory;
    private int msize;
    private int mcost;

    public planeter (String imageurl, String namn, String company, int location, String category, int size, int cost){
        mimageurl = imageurl;
        mnamn = namn;
        mcompany = company;
        mlocation = location;
        mcategory = category;
        msize = size;
        mcost = cost;

    }

    public String getimageurl() {
        return mimageurl;
    }

    public String getMnamn() {
        return mnamn;
    }

    public String getMcompany() {
        return mcompany;
    }

    public int getMlocation() {return mlocation;}

    public String getMcategory() {return mcategory;}

    public int getMsize() {return msize;}

    public int getMcost() {return mcost;}


}
