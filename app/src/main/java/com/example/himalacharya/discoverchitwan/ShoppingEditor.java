package com.example.himalacharya.discoverchitwan;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.himalacharya.discoverchitwan.data.ShoppingContract;
import com.example.himalacharya.discoverchitwan.data.ShoppingDBHelper;

public class ShoppingEditor extends AppCompatActivity {

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Find all relevant views that we will need to read user input from
        productNameText = (EditText) findViewById(R.id.product_name_text);
        productPriceText = (EditText) findViewById(R.id.price_input);
        productDescription = (EditText) findViewById(R.id.description_input);
    }

    private void insertProduct() {
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


        //Insert a new product into the provider,returning the content URI for the new product

        Uri uri = getContentResolver().insert(ShoppingContract.ShoppingEntry.CONTENT_URI, values);


        if (uri == null) {
            Toast.makeText(this, "Error in saving product ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

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
                insertProduct();

                //ExitActivity and returns to prevoius activity
                finish();
                return true;

            case R.id.action_delete:

                return true;

            case R.id.action_order_more:

                //save product to database
                insertProduct();

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
}
