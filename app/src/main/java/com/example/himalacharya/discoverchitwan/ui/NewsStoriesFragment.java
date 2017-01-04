package com.example.himalacharya.discoverchitwan.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.NewsStories;
import com.example.himalacharya.discoverchitwan.adapter.NewsStoriesAdapter;
import com.example.himalacharya.discoverchitwan.utils.QueryUtils;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsStoriesFragment extends Fragment {

    //Adapter for the lsit of news stories
    private NewsStoriesAdapter mAdapter;

    //URL to query the USGS dataset for earthquake information
    private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=techcrunch&apiKey=9d0e5500177b47398788ddd218deef6d";


    public NewsStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView =inflater.inflate(R.layout.fragment_news_stories,container,false);

        //Creating array of activities
        ArrayList<NewsStories> whatToDo=new ArrayList<>();

        mAdapter=new NewsStoriesAdapter(getActivity(),whatToDo);

        ListView listView= (ListView) rootView.findViewById(R.id.newsStoriesListView);
        listView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Find the current news that was clciked on
                NewsStories currentnews=mAdapter.getItem(position);

                //Convert the String URL into URI object (to pass inti Intent Constructor)
                Uri newsUri=Uri.parse(currentnews.getNewsURL());

                //Create a new intent to view the earthquake URi
                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,newsUri);

                //Start the intengt to launch a new activity
                startActivity(websiteIntent);
            }
        });

        //Start the Async Task to fetch the newsstories data
        new NewsStoriesAsyncTask().execute(NEWS_REQUEST_URL);

        return  rootView;


    }

    //Make an HTTP request to the given URL and return a string as the response



    private class NewsStoriesAsyncTask extends AsyncTask<String,Void,List<NewsStories>>{


        @Override
        protected List<NewsStories> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<NewsStories> result= QueryUtils.fetchNewsData(urls[0]);

            return  result;
        }

        @Override
        protected void onPostExecute(List<NewsStories> data){
            //Clear the previous adatper
            mAdapter.clear();

            // If there is a valid list of {@link NewsStories}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }


    }




}
