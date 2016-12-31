package com.example.himalacharya.discoverchitwan.adapter;

/**
 * Created by Himal Acharya on 2016-12-22.
 */

public class Location {
    public String name;
    public String address;
    public int imageResourceId=No_IMAGE_PROVIDED;

    private static final int No_IMAGE_PROVIDED=-1;

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location(String name, String address, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.imageResourceId=imageResourceId;
    }

    //returns whether it has image or not
    public boolean hasImage(){
        return imageResourceId!=No_IMAGE_PROVIDED;
    }
}
