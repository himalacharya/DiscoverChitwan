package com.example.himalacharya.discoverchitwan;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView locationTextView= (TextView) listItemView.findViewById(R.id.location);

        locationTextView.setText(location.getLocation());

        return listItemView;
    }
}
