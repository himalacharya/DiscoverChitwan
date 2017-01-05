package com.example.himalacharya.discoverchitwan.ui;

import android.content.Context;
import android.content.Intent;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


import android.support.v4.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.app.LoaderManager.LoaderCallbacks;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.NewsStories;
import com.example.himalacharya.discoverchitwan.adapter.NewsStoriesAdapter;
import com.example.himalacharya.discoverchitwan.utils.NewsStoriesLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsStoriesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsStories>> {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ProgressBar loadingIndicator;


    /**
     * +     * Constant value for the news stories loader ID. We can choose any integer.
     * +     * This really only comes into play if you're using multiple loaders.
     * +
     */

    private static final int NEWSSTORIES_LOADER_ID = 1;


    //Adapter for the lsit of news stories
    private NewsStoriesAdapter mAdapter;

    //URL to query the USGS dataset for earthquake information
    private static final String NEWS_REQUEST_URL = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=9d0e5500177b47398788ddd218deef6d";


    //Text view that is dispalyed when the list is empty
    private TextView mEmptyStateTextView;

    public NewsStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_news_stories, container, false);
        loadingIndicator = (ProgressBar) rootView.findViewById(R.id.loading_indicator);
        //Creating array of activities
        ArrayList<NewsStories> whatToDo = new ArrayList<>();
        mAdapter = new NewsStoriesAdapter(getActivity(), whatToDo);


        ListView listView = (ListView) rootView.findViewById(R.id.newsStoriesListView);

        ConnectivityManager connectivityManagerCompat= (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=connectivityManagerCompat.getActiveNetworkInfo();


        if (activeNetwork!=null&&activeNetwork.isConnected()){

            // Set an item click listener on the ListView, which sends an intent to a web browser
            // to open a website with more information about the selected news.
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //Find the current news that was clciked on
                    NewsStories currentnews = mAdapter.getItem(position);

                    //Convert the String URL into URI object (to pass inti Intent Constructor)
                    Uri newsUri = Uri.parse(currentnews.getNewsURL());

                    //Create a new intent to view the earthquake URi
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                    //Start the intengt to launch a new activity
                    startActivity(websiteIntent);
                }
            });

            //Lookup the swipe conatiner view
            swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_to_refresh_container);
            //Setup refresh listener which trigers new data loading
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //Code to refresh the list
                    //fetchTimelineAsync(0);
                }
            });
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWSSTORIES_LOADER_ID, null, this);
        }

        else{

            //loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view_news_stories);
            listView.setEmptyView(mEmptyStateTextView);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No innternet COnnection");

        }



        listView.setAdapter(mAdapter);



        return rootView;


    }

    @Override
    public Loader<List<NewsStories>> onCreateLoader(int id, Bundle args) {

        //create a new loader for the given url

        return new NewsStoriesLoader(getContext(), NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStories>> loader, List<NewsStories> newsStories) {

        //Set empty state text to dispaly "No news Stories found
        //mEmptyStateTextView.setText(R.string.no_newsstories);

        //clear te adapter of previous news stories data
        mAdapter.clear();

        if (newsStories != null && !newsStories.isEmpty()) {
            mAdapter.addAll(newsStories);
            loadingIndicator.setVisibility(View.GONE);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsStories>> loader) {
        mAdapter.clear();
    }


}
