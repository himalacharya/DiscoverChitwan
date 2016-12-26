package com.example.himalacharya.discoverchitwan.data;

import android.provider.BaseColumns;

/**
 * Created by Himal Acharya on 2016-12-25.
 */

public final class ShoppingContract {

    //To prevent someone from accidentally inisitaing
    //contract classs, making the calss constructor private


    private ShoppingContract() {
    }

    public static class ShoppingEntry implements BaseColumns{
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
