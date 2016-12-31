package com.example.himalacharya.discoverchitwan.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static com.example.himalacharya.discoverchitwan.data.ShoppingContract.CONTENT_AUTHORITY;
import static com.example.himalacharya.discoverchitwan.data.ShoppingContract.PATH_SHOPPING;

/**
 * Created by Himal Acharya on 2016-12-30.
 */

public class ShoppingProvider extends ContentProvider {

    private ShoppingDBHelper shoppingDBHelper;

    //URI Matcher code for the content URI for shoppinglist table
    private static final int PRODUCT = 100;

    //URI matcher code for the content URI for a single product in the shoppinglist table
    private static final int PRODUCT_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add 2 content URIs to URI matcher
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_SHOPPING, PRODUCT);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_SHOPPING + "/#", PRODUCT_ID);
    }


    @Override
    public boolean onCreate() {
        // TODO: Create and initialize a ShoopingDbHelper object to gain access to the shopping database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.

        shoppingDBHelper = new ShoppingDBHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        //Get readable database
        SQLiteDatabase sqLiteDatabase = shoppingDBHelper.getReadableDatabase();

        //Cursor will old the result of querty
        Cursor cursor;

        //Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT:

                cursor = sqLiteDatabase.query(ShoppingContract.ShoppingEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            case PRODUCT_ID:
                s = ShoppingContract.ShoppingEntry._ID + "=?";
                strings1 = new String[]{String.valueOf(ContentUris.parseId(uri))};

                //This will perform a query on the shopping list table where _id equals 3 to
                //return a Cursor containing that row of the table.
                cursor = sqLiteDatabase.query(ShoppingContract.ShoppingEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                break;

            default:
                throw new IllegalArgumentException("cannot qery known URI " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT:
                return ShoppingContract.ShoppingEntry.CONTENT_LIST_TYPE;

            case PRODUCT_ID:
                return ShoppingContract.ShoppingEntry.CONTENT_ITEM_TYPE;


            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);

        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        //Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT:
                return insertProduct(uri, contentValues);

            default:
                throw new IllegalArgumentException("Insertion not supported for " + uri);

        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        //Get Writable database
        SQLiteDatabase sqLiteDatabase = shoppingDBHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);


        switch (match) {
            case PRODUCT:

                return sqLiteDatabase.delete(ShoppingContract.ShoppingEntry.TABLE_NAME, s, strings);

            case PRODUCT_ID:
                // For the PRODUCT_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                s = ShoppingContract.ShoppingEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return sqLiteDatabase.delete(ShoppingContract.ShoppingEntry.TABLE_NAME, s, strings);

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);


        }


    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCT:
                return updateProduct(uri, contentValues, s, strings);

            case PRODUCT_ID:
                // For the PRODUCT_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                s = ShoppingContract.ShoppingEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, contentValues, s, strings);

            default:
                throw new IllegalArgumentException("Update not supported for " + uri);


        }

    }

    private Uri insertProduct(Uri uri, ContentValues contentValues) {

       /* Since there’s a TODO in the insertPet() method, let’s zoom in on that next. We already know we’re in the PETS case from the UriMatcher result, so we need to continue walking down the diagram and get a database object, and then do the insertion.

        Let’s start by getting a database object. Should it be a readable or writeable database? Well, we are editing the data source by adding a new pet, so we need to write changes to the database.
      */

        //Check that the name is not null
        String name = contentValues.getAsString(ShoppingContract.ShoppingEntry.COLUMN_NAME);
        if (name == null) {
            Toast.makeText(getContext(), "Product requires a name", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("Product requires a name");
        }

        Integer price = contentValues.getAsInteger(ShoppingContract.ShoppingEntry.COLUMN_PRICE);
        if (price < 0 && price != null) {
            throw new IllegalArgumentException("Product requires a valid price");
        }

        String description = contentValues.getAsString(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION);
        if (description == null) {
            throw new IllegalArgumentException("Product requires a description");
        }

        //Get writable database
        SQLiteDatabase sqLiteDatabase = shoppingDBHelper.getWritableDatabase();

        //insert the new product with the given values
        long newRowId = sqLiteDatabase.insert(ShoppingContract.ShoppingEntry.TABLE_NAME, null, contentValues);

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (newRowId == -1) {
            Log.e("Error", "Failed to insert row for " + uri);
            return null;
        } else {
            return ContentUris.withAppendedId(uri, newRowId);
        }


    }

    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // TODO: Update the selected pets in the pets database table with the given ContentValues

        // TODO: Return the number of rows that were affected
        //check name value is not null
        if (values.containsKey(ShoppingContract.ShoppingEntry.COLUMN_NAME)) {
            String name = values.getAsString(ShoppingContract.ShoppingEntry.COLUMN_NAME);
            if (name == null) {

                throw new IllegalArgumentException("Product reqires a name ");

            }

        }

        //check the price value is valid
        if (values.containsKey(ShoppingContract.ShoppingEntry.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(ShoppingContract.ShoppingEntry.COLUMN_PRICE);
            if (price < 0 && price != null) {
                throw new IllegalArgumentException("Product requires a valid price");
            }
        }

        //check the description is valid
        if (values.containsKey(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION)) {
            String description = values.getAsString(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION);
            if (description == null) {
                throw new IllegalArgumentException("Product requires a description");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = shoppingDBHelper.getWritableDatabase();

        // Returns the number of database rows affected by the update statement
        return database.update(ShoppingContract.ShoppingEntry.TABLE_NAME, values, selection, selectionArgs);

    }
}
