package com.example.himalacharya.discoverchitwan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    //For app icon control for nav drawer
    ActionBarDrawerToggle mDrawerToggle;

    //Setting properties for proper title dsiplay
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list_white_24dp);
        mTitle = mDrawerTitle = getTitle();


        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        String[] data = getResources().getStringArray(R.array.navigation_drawer_items_array);

        //List the drawer items
        ObjectDrawerItem[] drawerItems = new ObjectDrawerItem[5];

        drawerItems[0] = new ObjectDrawerItem(data[0]);
        drawerItems[1] = new ObjectDrawerItem(data[1]);
        drawerItems[2] = new ObjectDrawerItem(data[2]);
        drawerItems[3] = new ObjectDrawerItem(data[3]);
        drawerItems[4] = new ObjectDrawerItem(data[4]);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.color_green, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        //Set the drawer toggle as the DrawListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);


        //Creating the adapter
        DrawerItemCustomAdapter drawerItemCustomAdapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItems);

        //Set the adapter
        mDrawerList.setAdapter(drawerItemCustomAdapter);

        //set the itemclick listener

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



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
       /* //Find the view pager that will allow the user to swipe between fragments
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
        tabLayout.setupWithViewPager(viewPager)*/
        ;

        if (savedInstanceState == null) {
           new DrawerItemClickListener().selectItem(0);
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu options from .xml file
        // this adds the items in the app bar
        getMenuInflater().inflate(R.menu.menu_shopping,menu);
        return true;
    }

    */

    public class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new SeeAndDoFragment();
                    break;
                case 1:
                    startActivity(new Intent(getApplicationContext(),Shopping.class));
                    break;
                case 2:
                    fragment = new DineFragment();
                    break;
                case 3:
                    fragment = new StayFragment();
                    break;
                case 4:
                    fragment = new NewsStoriesFragment();
                    break;
                default:
                    fragment = new SeeAndDoFragment();
                    break;

            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                //Hightlist the selected item, update the title and close the drawe
                mDrawerList.setItemChecked(position, true);
                setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setTitle(CharSequence mTitle) {
        this.mTitle = mTitle;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Sync the toggle state after on RestoreInstanceState has occured
        mDrawerToggle.syncState();
    }


    //Called whenever we call invalidateOptionMenu()

}


