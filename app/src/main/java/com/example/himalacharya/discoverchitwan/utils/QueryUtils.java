package com.example.himalacharya.discoverchitwan.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.NewsStories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himal Acharya on 2017-01-04.
 */

public class QueryUtils {

    //Tag for Log messages

    public static final String LOG_TAG=QueryUtils.class.getSimpleName();

    //Query the News API data and return an object to represent a single news

    public static List<NewsStories> fetchNewsData(String requestUrl){
        //create a URL object
        URL url=createUrl(requestUrl);

        //Perform  HTTP request to the URL and receive a JSON rsponse back
        String jsonResponse=null;

        try {
            jsonResponse=makeHTTPRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Error closing input Stream",e);

        }

        //Extract relevant fields from the JSON and create a list
        List<NewsStories> newsStories=extractFeatureFromJson(jsonResponse);

        //return a list
        return newsStories;




    }

    //Return new URL object from the given string URL
    private static URL createUrl(String stringURL){
        URL url=null;

        try {
            url=new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Error with creating URL ",e);

        }

        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();


        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return an {@link NewsStories} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */

    private static List<NewsStories> extractFeatureFromJson(String newsstoriesJSON){

        //If the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(newsstoriesJSON)){
            return null;
        }

        //Create an empt ArrayList that we can start adding news stories
        List<NewsStories> newsStoriesList=new ArrayList<>();


        try{

            JSONObject baseJsonResponse=new JSONObject(newsstoriesJSON);
            JSONArray featureArray=baseJsonResponse.getJSONArray("articles");

            //For each news array , create an Object
            for (int i=0;i<featureArray.length();i++){


                //Get a single news at position within the list of newsstories
                JSONObject currentnews=featureArray.getJSONObject(i);

                //Extract the author , logo, date, description news
                String author=currentnews.getString("author");

                String urlToImage=currentnews.getString("urlToImage");

                String publishedAt=currentnews.getString("publishedAt");


                String description=currentnews.getString("description");

                String newsURL=currentnews.getString("url");

                NewsStories newsStories=new NewsStories(author,description, R.drawable.abc,publishedAt,newsURL);

                //Add news to the lsit of news stories
                newsStoriesList.add(newsStories);


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsStoriesList;
    }

}
