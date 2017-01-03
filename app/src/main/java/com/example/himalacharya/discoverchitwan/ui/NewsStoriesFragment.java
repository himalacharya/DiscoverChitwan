package com.example.himalacharya.discoverchitwan.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.NewsStories;
import com.example.himalacharya.discoverchitwan.adapter.NewsStoriesAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsStoriesFragment extends Fragment {


    public NewsStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_news_stories,container,false);

        //Creating array of activities
        ArrayList<NewsStories> whatToDo=new ArrayList<>();

        NewsStoriesAdapter adapter=new NewsStoriesAdapter(getActivity(),whatToDo);


        whatToDo.add(new NewsStories("ABC","Nepal consitution ammendentment",R.drawable.abc));
        whatToDo.add(new NewsStories("BBC","President Obama",R.drawable.bbc));
        whatToDo.add(new NewsStories("CNN","Ice hockey Live stremaing",R.drawable.cnn));

        ListView listView= (ListView) rootView.findViewById(R.id.newsStoriesListView);
        listView.setAdapter(adapter);

        return  rootView;


    }

}
