package com.example.himalacharya.discoverchitwan.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.Location;
import com.example.himalacharya.discoverchitwan.adapter.LocationAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAndDoFragment extends Fragment {


    public SeeAndDoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView =inflater.inflate(R.layout.fragment_see_and_do,container,false);

        //Creating array of activities
        ArrayList<Location> whatToDo=new ArrayList<>();

        LocationAdapter adapter=new LocationAdapter(getActivity(),whatToDo);

        ListView listView= (ListView) rootView.findViewById(R.id.seenanddolistView);
        listView.setAdapter(adapter);

        whatToDo.add(new Location("Elephant Back Safari","Sauraha",R.drawable.family_daughter));
        whatToDo.add(new Location("Jeep Safari","Kassara",R.drawable.family_mother));
        whatToDo.add(new Location("Visit Elephant Breeding Centre","Baghmara",R.drawable.family_older_sister));
        whatToDo.add(new Location("narayani Motor Boat","Jalbire",R.drawable.color_dusty_yellow));
        whatToDo.add(new Location("Village Walk","Korak",R.drawable.number_ten));
        return  rootView;



    }

}
