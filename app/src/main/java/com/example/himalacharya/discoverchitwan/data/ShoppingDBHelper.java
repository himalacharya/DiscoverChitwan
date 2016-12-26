package com.example.himalacharya.discoverchitwan.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Himal Acharya on 2016-12-25.
 */

public class ShoppingDBHelper extends SQLiteOpenHelper{

    //If you change the database schema, we must increment the database version
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Shopping.db";

    public ShoppingDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_ENTRIES="CREATE TABLE "+
                ShoppingContract.ShoppingEntry.TABLE_NAME+"("+
                ShoppingContract.ShoppingEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ShoppingContract.ShoppingEntry.COLUMN_NAME+" TEXT NOT NULL, "+
                ShoppingContract.ShoppingEntry.COLUMN_PRICE+" INTEGER NOT NULL, "+
                ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK+" INTEGER NOT NULL, "+
                ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION+" TEXT);";


        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //This database is only a cache for online data, so its upgrade policy is
        //simply to discard the data and start over

        String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+ ShoppingContract.ShoppingEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
