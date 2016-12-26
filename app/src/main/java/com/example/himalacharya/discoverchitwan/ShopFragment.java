package com.example.himalacharya.discoverchitwan;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.data.ShoppingContract;
import com.example.himalacharya.discoverchitwan.data.ShoppingDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.activity_shop,container,false);

        Intent intent=new Intent(getActivity(),ShoppingActivity.class);
        startActivity(intent);

        //displaying Database info
       // displayDatabaseinfo();
        return rootView;

    }


    private void displayDatabaseinfo(){

        //To access database , first initiated the subclass of SQLITEOpenhelper

        ShoppingDBHelper shoppingDBHelper=new ShoppingDBHelper(getActivity());

        //Get the data repository in write mode
        SQLiteDatabase database=shoppingDBHelper.getWritableDatabase();

        /*//Create a new map of values, where column names are the keys
        ContentValues values=new ContentValues();
        values.put(ShoppingContract.ShoppingEntry._ID,);

*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
