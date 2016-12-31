package com.example.himalacharya.discoverchitwan.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Himal Acharya on 2016-12-25.
 */

public final class ShoppingContract {

    public static final String CONTENT_AUTHORITY="com.example.himalacharya.discoverchitwan";

    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);

    //Path_TableName
    public static final String PATH_SHOPPING="shoppinglist";


    //To prevent someone from accidentally initiating
    //contract class, making the calss constructor private


    private ShoppingContract() {
    }

    public static class ShoppingEntry implements BaseColumns{

        //completing CONTENT_URI
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_SHOPPING);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOPPING;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOPPING;



        public static final String TABLE_NAME="shoppinglist";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_PRICE="price";
        public static final String COLUMN_IN_STOCK="in_stock";
        public static final String COLUMN_DESCRIPTION="description";

        public static final int STOCK_IN=1;
        public static final int STOCk_OUT=0;

    }
}
