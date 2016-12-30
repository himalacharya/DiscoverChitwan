package com.example.himalacharya.discoverchitwan.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import static com.example.himalacharya.discoverchitwan.data.ShoppingContract.CONTENT_AUTHORITY;
import static com.example.himalacharya.discoverchitwan.data.ShoppingContract.PATH_SHOPPING;

/**
 * Created by Himal Acharya on 2016-12-30.
 */

public class ShoppingProvider extends ContentProvider {

    private ShoppingDBHelper shoppingDBHelper;

    //URI Matcher code for the content URI for shoppinglist table
    private static final int PRODUCT=100;

    //URI matcher code for the content URI for a single product in the shoppinglist table
    private static final int PRODUCT_ID=101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add 2 content URIs to URI matcher
        sUriMatcher.addURI(CONTENT_AUTHORITY,PATH_SHOPPING,PRODUCT);
        sUriMatcher.addURI(CONTENT_AUTHORITY,PATH_SHOPPING+"/#",PRODUCT_ID);
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
        SQLiteDatabase sqLiteDatabase=shoppingDBHelper.getReadableDatabase();

        //Cursor will old the result of querty
        Cursor cursor;

        //Figure out if the URI matcher can match the URI to a specific code
        int match=sUriMatcher.match(uri);

        switch (match){
            case PRODUCT:

                cursor=sqLiteDatabase.query(ShoppingContract.ShoppingEntry.TABLE_NAME,strings,s,strings1,null,null,s1);
                break;

            case PRODUCT_ID:
                s= ShoppingContract.ShoppingEntry._ID+"=?";
                strings1=new String[]{String.valueOf(ContentUris.parseId(uri))};

                //This will perform a query on the shopping list table where _id equals 3 to
                //return a Cursor containing that row of the table.
                cursor=sqLiteDatabase.query(ShoppingContract.ShoppingEntry.TABLE_NAME,strings,s,strings1,null,null,s1);
                break;

            default:
                throw new IllegalArgumentException("cannot qery known URI "+uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
