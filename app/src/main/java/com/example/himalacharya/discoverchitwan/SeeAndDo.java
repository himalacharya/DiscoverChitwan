package com.example.himalacharya.discoverchitwan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SeeAndDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_and_do);

        //Creating array of activities
        ArrayList<Location> whatToDo=new ArrayList<>();

        LocationAdapter adapter=new LocationAdapter(this,whatToDo);

        ListView listView= (ListView) findViewById(R.id.seenanddolistView);
        listView.setAdapter(adapter);

        whatToDo.add(new Location("Elephant Back Safari","Sauraha"));
        whatToDo.add(new Location("Jeep Safari","Kassara"));
        whatToDo.add(new Location("Visit Elephant Breeding Centre","Baghmara"));
        whatToDo.add(new Location("narayani Motor Boat","Jalbire"));
        whatToDo.add(new Location("Village Walk","Korak"));



    }
}
