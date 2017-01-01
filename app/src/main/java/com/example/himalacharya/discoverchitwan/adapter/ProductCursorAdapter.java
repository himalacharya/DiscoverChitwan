package com.example.himalacharya.discoverchitwan.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himalacharya.discoverchitwan.R;
import com.example.himalacharya.discoverchitwan.data.ShoppingContract;

/**
 * Created by Himal Acharya on 2016-12-31.
 */

public class ProductCursorAdapter extends CursorAdapter {
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO: Fill out this method and return the list item view (instead of null)
        return LayoutInflater.from(context).inflate(R.layout.listview_location_shopping, parent, false);


    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Find the fields to populate in inflated template
        TextView tvProductName = (TextView) view.findViewById(R.id.item_shopping_productname);
        TextView tvProductPrice = (TextView) view.findViewById(R.id.item_shopping_price);
        TextView tvProductStock = (TextView) view.findViewById(R.id.item_shopping_stock);
        TextView tvProductDescription = (TextView) view.findViewById(R.id.item_shopping_description);

        //Extract properties from Cursor
        String productName = cursor.getString(cursor.getColumnIndexOrThrow(ShoppingContract.ShoppingEntry.COLUMN_NAME));
        int productPrice = cursor.getInt(cursor.getColumnIndexOrThrow(ShoppingContract.ShoppingEntry.COLUMN_PRICE));
        int productStock = cursor.getInt(cursor.getColumnIndexOrThrow(ShoppingContract.ShoppingEntry.COLUMN_IN_STOCK));
        String productDescription = cursor.getString(cursor.getColumnIndexOrThrow(ShoppingContract.ShoppingEntry.COLUMN_DESCRIPTION));

        //Adding not written description in UI
        if(TextUtils.isEmpty(productDescription)){
            productDescription=context.getString(R.string.no_description_provided);
        }
        //Populated field with extracted properties
        tvProductName.setText(productName);
        tvProductPrice.setText(String.valueOf(productPrice));
        tvProductStock.setText(String.valueOf(productStock));
        tvProductDescription.setText(productDescription);

    }
}
