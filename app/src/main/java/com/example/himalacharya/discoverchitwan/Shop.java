package com.example.himalacharya.discoverchitwan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_shop, new ShopFragment())
                .commit();
    }
}
