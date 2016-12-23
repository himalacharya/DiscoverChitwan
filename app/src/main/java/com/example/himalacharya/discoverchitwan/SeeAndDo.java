package com.example.himalacharya.discoverchitwan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;
public class SeeAndDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_and_do);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_see_and_do, new SeeAndDoFragment())
                .commit();
    }


}
