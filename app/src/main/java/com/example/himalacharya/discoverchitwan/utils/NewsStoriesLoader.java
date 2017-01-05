package com.example.himalacharya.discoverchitwan.utils;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;


import com.example.himalacharya.discoverchitwan.adapter.NewsStories;

import java.util.List;

/**
 * Created by Himal Acharya on 2017-01-04.
 */

public class NewsStoriesLoader extends AsyncTaskLoader<List<NewsStories>> {

    private  String mUrl;

    public NewsStoriesLoader (Context context, String mUrl){
        super(context);
        this.mUrl=mUrl;


    }
    @Override
    protected   void onStartLoading(){
        forceLoad();
    }

    public List<NewsStories> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }

        List<NewsStories> result = QueryUtils.fetchNewsData(mUrl);

        return result;
    }


}
