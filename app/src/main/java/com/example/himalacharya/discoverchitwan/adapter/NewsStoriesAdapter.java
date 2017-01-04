package com.example.himalacharya.discoverchitwan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himal Acharya on 2017-01-02.
 */

public class NewsStoriesAdapter extends ArrayAdapter<NewsStories> {


    public NewsStoriesAdapter(Context context,ArrayList<NewsStories> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the object at the location in the list
        NewsStories newsStories=getItem(position);
        View rootView=convertView;

        if (rootView==null){
            rootView=LayoutInflater.from(getContext()).inflate(R.layout.list_view_news_stories,parent,false);

        }
        //Find the text view in list_view_news_stories.xml

        TextView textView= (TextView) rootView.findViewById(R.id.news_channel_author);
        textView.setText(newsStories.getNewsChannelName());

        TextView textView2= (TextView) rootView.findViewById(R.id.news_channel_date);
        textView2.setText(newsStories.getDate());

        ImageView imageView= (ImageView) rootView.findViewById(R.id.news_channel_logo);

        if (newsStories.hasImage()) {

            //set the Imageview to the image resource specified in the current word
            imageView.setImageResource(newsStories.getImageResourceId());

            //make sure image is visible

            imageView.setVisibility(View.VISIBLE);
        }else{
            //Otherwise hide the image
            imageView.setVisibility(View.GONE);
        }

        TextView textView1= (TextView) rootView.findViewById(R.id.news_channel_scrollingnews);
        textView1.setText(newsStories.getDescriptionNewsStory());
        return rootView;
    }
}


