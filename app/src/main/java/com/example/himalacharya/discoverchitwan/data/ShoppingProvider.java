package com.example.himalacharya.discoverchitwan.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Himal Acharya on 2016-12-30.
 */

public class ShoppingProvider extends ContentProvider {

    private ShoppingDBHelper shoppingDBHelper;
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
        return null;
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
