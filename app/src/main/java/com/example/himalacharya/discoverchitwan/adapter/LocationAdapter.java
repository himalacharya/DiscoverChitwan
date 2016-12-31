package com.example.himalacharya.discoverchitwan.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.R;

import java.util.ArrayList;

/**
 * Created by Himal Acharya on 2016-12-22.
 */

public class LocationAdapter extends ArrayAdapter<Location> {

    public LocationAdapter(Activity context, ArrayList<Location> locations) {
        super(context,0,locations);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //Get the object located at this position in the list
        Location location=getItem(position);

        //check if the existing view is being reused, otherwise inflate the view
        View listItemView=convertView;
        if (listItemView==null){

            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.item_location,parent,false);

        }

        //Find the TextView in list_location.xml layout with ID
        TextView nameTextView= (TextView) listItemView.findViewById(R.id.name);

        nameTextView.setText(location.getName());

        TextView locationTextView= (TextView) listItemView.findViewById(R.id.address);

        locationTextView.setText(location.getAddress());

        ImageView imageView= (ImageView) listItemView.findViewById(R.id.images);

        if (location.hasImage()) {

            //set the Imageview to the image resource specified in the current word
            imageView.setImageResource(location.getImageResourceId());

            //make sure image is visible

            imageView.setVisibility(View.VISIBLE);
        }else{
            //Otherwise hide the image
            imageView.setVisibility(View.GONE);
        }
              //Return the whole list
        return listItemView;
    }
}
