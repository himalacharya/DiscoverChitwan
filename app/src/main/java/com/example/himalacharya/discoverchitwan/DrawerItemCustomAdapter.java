package com.example.himalacharya.discoverchitwan;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Himal Acharya on 2016-12-27.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<ObjectDrawerItem> {

    Context context;
    int resource;
    ObjectDrawerItem objects[]=null;


    public DrawerItemCustomAdapter(Context context, int resource, ObjectDrawerItem[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView=convertView;

        LayoutInflater layoutInflater=((Activity) context).getLayoutInflater();
        listView=layoutInflater.inflate(resource,parent,false);

        TextView textViewName= (TextView) listView.findViewById(R.id.textViewName);

        ObjectDrawerItem folder=objects[position];

        textViewName.setText(folder.name);

        return listView;


    }
}
