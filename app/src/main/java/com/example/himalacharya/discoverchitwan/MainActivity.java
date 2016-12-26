package com.example.himalacharya.discoverchitwan;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* //FInd the view that shows the category
        Button seeAndDoActivity= (Button) findViewById(R.id.activity_see_and_do);
        seeAndDoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeAndDoIntent=new Intent(MainActivity.this,SeeAndDo.class);
                //start the activity
                startActivity(seeAndDoIntent);
            }
        });


        //FInd the view that shows the category
        Button shopActivity= (Button) findViewById(R.id.activity_shop);
        shopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shopIntent=new Intent(MainActivity.this,Shop.class);
                //start the activity
                startActivity(shopIntent);
            }
        });



        //FInd the view that shows the category
        final Button dineActivity= (Button) findViewById(R.id.activity_dine);
        dineActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dineIntent=new Intent(MainActivity.this,Dine.class);
                //start the activity
                startActivity(dineIntent);
            }
        });



        //FInd the view that shows the category
        Button stayActivity= (Button) findViewById(R.id.activity_stay);
        stayActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stayIntent=new Intent(MainActivity.this,Stay.class);
                //start the activity
                startActivity(stayIntent);
            }
        });
*/
        //Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);

        //Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter=new SimpleFragmentPagerAdapter(this,getSupportFragmentManager());

        //Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Find the Tab Layout that shows the tabs
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);

        //Connect the tab layout with the viewPager
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
    }
}


