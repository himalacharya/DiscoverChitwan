package com.example.himalacharya.discoverchitwan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.himalacharya.discoverchitwan.data.ShoppingContract;
import com.example.himalacharya.discoverchitwan.data.ShoppingDBHelper;

public class Shopping extends AppCompatActivity {

    //To access database , first initiated the subclass of SQLITEOpenhelper

    ShoppingDBHelper shoppingDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShoppingEditor.class);
                startActivity(intent);

            }
        });


        shoppingDBHelper = new ShoppingDBHelper(this);

        //displaying Database info
        displayDatabaseinfo();
    }

    private void displayDatabaseinfo() {


        //Get the data repository in write mode
        /*SQLiteDatabase database = shoppingDBHelper.getWritableDatabase();
*/

        String[] projections= {
                ShoppingContract.ShoppingEntry._ID,
                ShoppingContract.ShoppingEntry.COLUMN_NAME,
                ShoppingContract.ShoppingEntry.COLUMN_PRICE,
                ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK,
                ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION
        };

        //Performs this  sql query
      /*  Cursor cursor = database.query(ShoppingContract.ShoppingEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null,
                null);*/

        Cursor cursor=getContentResolver().query(ShoppingContract.ShoppingEntry.CONTENT_URI,projections,null,null,null);

        TextView displayView = (TextView) findViewById(R.id.item_shopping_productname);

        try {

            Log.e("test", cursor.getCount() + "");
            int coount = cursor.getCount();
            displayView.append(ShoppingContract.ShoppingEntry._ID+" -" +ShoppingContract.ShoppingEntry.COLUMN_NAME+
                    " - "+ShoppingContract.ShoppingEntry.COLUMN_PRICE+" - "+ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK+
                    " - "+ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION);

            //Figure out the index of each column
            int idColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry._ID);
            int nameColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_NAME);
            int priceColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_PRICE);
            int instockColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK);
            int descriptionColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION);

            //iterate throough all the returned rows in the cursor
            while (cursor.moveToNext()){

                //use that index to extract the String or int value of the word
                //at the current row the cursor is on

                int currentId=cursor.getInt(idColumnIndex);
                String currentName=cursor.getString(nameColumnIndex);
                int currentPrice=cursor.getInt(priceColumnIndex);
                int currentStock=cursor.getInt(instockColumnIndex);
                String currentDescription=cursor.getString(descriptionColumnIndex);


                //Display the values from each column of the current row in the cursor in the TextView
                displayView.append("\n"+currentId+" - "+currentName+
                            " - "+currentPrice+" - "+
                            " - "+currentStock+
                            " - "+currentDescription);

            }

        } finally {

            cursor.close();
        }


    }

    private void insertProduct() {
        //Get the data repository in write mode
        SQLiteDatabase database = shoppingDBHelper.getWritableDatabase();

        //Create a new map of values, where column names are the keys

        ContentValues values = new ContentValues();
        values.put(ShoppingContract.ShoppingEntry.COLUMN_NAME, "Pashimna ");
        values.put(ShoppingContract.ShoppingEntry.COLUMN_PRICE, "2200");
        values.put(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK, ShoppingContract.ShoppingEntry.STOCK_IN);
        values.put(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION, " Kashmeree ");


        //Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(ShoppingContract.ShoppingEntry.TABLE_NAME, null, values);

        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        Log.v("Shopping ", "New RowID " + newRowId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //User clicked a menu option in toolbar overflow menu
        switch (menuItem.getItemId()) {
            //respond to click when "insert dummy data" is clicked"
            case R.id.action_insert_dummy_data:
                //inserting data
                insertProduct();
                displayDatabaseinfo();
                return true;

            case R.id.action_delete_all_entries:

                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDatabaseinfo();
    }

    @Override
    protected void onDestroy() {
        shoppingDBHelper.close();
        super.onDestroy();
    }
}
