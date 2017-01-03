package com.example.himalacharya.discoverchitwan.adapter;

/**
 * Created by Himal Acharya on 2017-01-02.
 */

public class NewsStories {

    private String newsChannelName;
    private String descriptionNewsStory;
    public int imageResourceId=No_IMAGE_PROVIDED;

    private static final int No_IMAGE_PROVIDED=-1;

    public String getNewsChannelName() {
        return newsChannelName;
    }

    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public String getDescriptionNewsStory() {
        return descriptionNewsStory;
    }

    public void setDescriptionNewsStory(String descriptionNewsStory) {
        this.descriptionNewsStory = descriptionNewsStory;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public NewsStories(String newsChannelName, String descriptionNewsStory, int imageResourceId) {
        this.newsChannelName = newsChannelName;
        this.descriptionNewsStory = descriptionNewsStory;
        this.imageResourceId = imageResourceId;
    }

    //returns whether it has image or not
    public boolean hasImage(){
        return imageResourceId!=No_IMAGE_PROVIDED;
    }

}
