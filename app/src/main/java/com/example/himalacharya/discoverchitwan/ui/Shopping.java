package com.example.himalacharya.discoverchitwan.ui;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.adapter.ProductCursorAdapter;
import com.example.himalacharya.discoverchitwan.data.ShoppingContract;

public class Shopping extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;

    ProductCursorAdapter productCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.fragment_shopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShoppingEditor.class);
                startActivity(intent);

            }
        });

        // Find ListView to populate
        ListView shoppingListItems = (ListView) findViewById(R.id.shoplistView);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        shoppingListItems.setEmptyView(emptyView);

        // Setup cursor adapter using cursor from last step
        productCursorAdapter = new ProductCursorAdapter(this, null);

        // Attach cursor adapter to the ListView
        shoppingListItems.setAdapter(productCursorAdapter);

        //Setting item click listener
        shoppingListItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(Shopping.this, ShoppingEditor.class);

                //For content URi, with specific id
                Uri currentProductUri = ContentUris.withAppendedId(ShoppingContract.ShoppingEntry.CONTENT_URI, id);

                //set the URI on the data field of the intent
                intent.setData(currentProductUri);

                startActivity(intent);
            }
        });


        //Kick off the loader
        getSupportLoaderManager().initLoader(PRODUCT_LOADER, null, this);


        // insertProduct();
        //displaying Database info
        //displayDatabaseinfo();
    }

/*    private void displayDatabaseinfo() {


        //Get the data repository in write mode
        *//*SQLiteDatabase database = shoppingDBHelper.getWritableDatabase();
*//*

        String[] projections= {
                ShoppingContract.ShoppingEntry._ID,
                ShoppingContract.ShoppingEntry.COLUMN_NAME,
                ShoppingContract.ShoppingEntry.COLUMN_PRICE,
                ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK,
                ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION
        };

        //Performs this  sql query
      *//*  Cursor cursor = database.query(ShoppingContract.ShoppingEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null,
                null);*//*

        Cursor cursor=getContentResolver().query(ShoppingContract.ShoppingEntry.CONTENT_URI,
                projections,
                null,
                null,
                null);

        // Find ListView to populate
        ListView shoppingListItems = (ListView) findViewById(R.id.shoplistView);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        shoppingListItems.setEmptyView(emptyView);

        // Setup cursor adapter using cursor from last step
        ProductCursorAdapter productCursorAdapter = new ProductCursorAdapter(this, cursor);

        // Attach cursor adapter to the ListView
        shoppingListItems.setAdapter(productCursorAdapter);


       *//* TextView displayView = (TextView) findViewById(R.id.display);

      *//**//*  int count=cursor.getCount();
        displayView.setText("No of row "+count);
*//**//*
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
*//*
    }*/

    private void insertProduct() {
       /* //Get the data repository in write mode
        SQLiteDatabase database = shoppingDBHelper.getWritableDatabase();
*/
        //Create a new map of values, where column names are the keys

        ContentValues values = new ContentValues();
        values.put(ShoppingContract.ShoppingEntry.COLUMN_NAME, "Pashimna ");
        values.put(ShoppingContract.ShoppingEntry.COLUMN_PRICE, "2200");
        values.put(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK, ShoppingContract.ShoppingEntry.STOCK_IN);
        values.put(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION, " Kashmeree ");


       /* //Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(ShoppingContract.ShoppingEntry.TABLE_NAME, null, values);
*/
        Uri uri = getContentResolver().insert(ShoppingContract.ShoppingEntry.CONTENT_URI, values);
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

    }

    public void deleteAllProduct() {
        getContentResolver().delete(ShoppingContract.ShoppingEntry.CONTENT_URI, null, null);

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
                //displayDatabaseinfo();
                return true;

            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;



        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projections = {
                ShoppingContract.ShoppingEntry._ID,
                ShoppingContract.ShoppingEntry.COLUMN_NAME,
                ShoppingContract.ShoppingEntry.COLUMN_PRICE,
                ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK,
                ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION
        };

        // This loader will execute the COntentProvider to query methd in a background thread
        return new CursorLoader(this,   //Parent activity context
                ShoppingContract.ShoppingEntry.CONTENT_URI, //Provides content URI to query
                projections,//columns to include in the resulting cursor
                null, //no selection clause
                null, //no selection arguments
                null);// default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Update ProductCursorAdapter with this new cursor containing updated product data
        productCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //callback called when the data needs to be delted
        productCursorAdapter.swapCursor(null);

    }

    private void showDeleteConfirmationDialog() {
        //Create an AlertDialog.Builder and set the message and click listeners
        //for the positive and negative buttons the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all_entries);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //User Clicked the "Delete " button, so delete the product
                deleteAllProduct();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //User cllicked the cancel button, so dismiss the dialog
                //and continue editing the product

                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });

        //CREAte and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
