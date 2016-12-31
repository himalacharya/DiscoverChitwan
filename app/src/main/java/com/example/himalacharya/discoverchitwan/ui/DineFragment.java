package com.example.himalacharya.discoverchitwan.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DineFragment extends Fragment {


    public DineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.activity_dine,container,false);

        return rootView;

    }

}
