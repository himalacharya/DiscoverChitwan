package com.example.himalacharya.discoverchitwan.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.data.ShoppingContract;
import com.example.himalacharya.discoverchitwan.data.ShoppingDBHelper;

public class ShoppingEditor extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //Content URI for the existing product (null if its new product)
    private Uri mCurrentProductUri;

    //Identifier for the product data loader
    private static final int EXISTING_PRODUCT_LOADER=0;

    //EditText firled to enter the product name
    private EditText productNameText;

    //EditText filed for price
    private EditText productPriceText;

    //EditText field for description
    private EditText productDescription;

    //Create database helper
    ShoppingDBHelper shoppingDBHelper = new ShoppingDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_editor);


        //Examine the intent that was used to launch this activity
        //in order to figure out if were creating a new product or editing an existing one
        Intent intent=getIntent();
        //Uri currentProductUri=intent.getData();
        mCurrentProductUri=intent.getData();

        //If the intent doesn't contain a product content URI, we know
        //that we are creating a new product

        if (mCurrentProductUri==null){
            setTitle(getString(R.string.add_product));
        } else{
            setTitle(getString(R.string.edit_product));
            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getSupportLoaderManager().initLoader(EXISTING_PRODUCT_LOADER,null,this);

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Kick off loader


        //Find all relevant views that we will need to read user input from
        productNameText = (EditText) findViewById(R.id.product_name_text);
        productPriceText = (EditText) findViewById(R.id.price_input);
        productDescription = (EditText) findViewById(R.id.description_input);
    }

    private void saveProduct() {
        //Read from input fields
        //Use trim to eliminate leading or trailing white space
        String productnameString = productNameText.getText().toString().trim();
        String productpriceString = productPriceText.getText().toString().trim();
        String productdescriptionString = productDescription.getText().toString().trim();

        int productpriceInt = Integer.parseInt(productpriceString);

        /*//Get the database in writemode
        SQLiteDatabase database=shoppingDBHelper.getWritableDatabase();
*/
        //Create a new map of values, where column names are the keys

        ContentValues values = new ContentValues();
        values.put(ShoppingContract.ShoppingEntry.COLUMN_NAME, productnameString);
        values.put(ShoppingContract.ShoppingEntry.COLUMN_PRICE, productpriceInt);
        values.put(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK, ShoppingContract.ShoppingEntry.STOCK_IN);
        values.put(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION, productdescriptionString);

        //Determine if this is a new or existing product by checking if mCurrentProductUri is null or nt

        if(mCurrentProductUri==null){

            //This is a new product , so insert a
            //Insert a new product into the provider,returning the content URI for the new product

            Uri uri = getContentResolver().insert(ShoppingContract.ShoppingEntry.CONTENT_URI, values);

            if (uri == null) {
                Toast.makeText(this, "Error in saving product ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            }

        }else{
            int rowsAffected=getContentResolver().update(mCurrentProductUri,values,null,null);

            //show message whether update was successful
            if (rowsAffected==0){
                //if no rows affected there was error in update
                Toast.makeText(this, R.string.update_failed,Toast.LENGTH_SHORT).show();
            }else{
                //update successful
                Toast.makeText(this, R.string.update_success,Toast.LENGTH_SHORT).show();
            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_edit, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_save:

                //save product to database
               saveProduct();

                //ExitActivity and returns to prevoius activity
                finish();
                return true;

            case R.id.action_delete:

                return true;

            case R.id.action_order_more:

                //save product to database
                saveProduct();

                //ExitActivity and returns to prevoius Activity
                finish();
                return true;


            case android.R.id.home:
                //Navigate back to parent Activity(Shopping)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onDestroy() {
        shoppingDBHelper.close();
        super.onDestroy();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projections= {
                ShoppingContract.ShoppingEntry._ID,
                ShoppingContract.ShoppingEntry.COLUMN_NAME,
                ShoppingContract.ShoppingEntry.COLUMN_PRICE,
                ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK,
                ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION
        };
        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentProductUri,         // Query the content URI for the current product
                projections,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {

            int nameColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_NAME);
            int priceColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_PRICE);
            int instockColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK);
            int descriptionColumnIndex=cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION);


            //use that index to extract the String or int value of the word
            //at the current row the cursor is on

            String currentName=cursor.getString(nameColumnIndex);
            int currentPrice=cursor.getInt(priceColumnIndex);
            int currentStock=cursor.getInt(instockColumnIndex);
            String currentDescription=cursor.getString(descriptionColumnIndex);

            //Update the views on the screen with the values from the database
            productNameText.setText(currentName);
            productPriceText.setText(Integer.toString(currentPrice));
            productDescription.setText(currentDescription);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        //if the loader is invalidated , clear out all the data from input view loads
        productNameText.setText("");
        productPriceText.setText("");
        productDescription.setText("");

    }
}