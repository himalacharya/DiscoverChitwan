package com.example.himalacharya.discoverchitwan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Himal Acharya on 2016-12-23.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    //Context of the app
    private Context context;

    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new SeeAndDoFragment();
        }else if(position==1){
            return new ShopFragment();
        }else if (position==2){
            return new DineFragment();
        }else if(position==3) {
            return new StayFragment();
        }else{
            return new NewsStoriesFragment();
        }

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return context.getString(R.string.see_and_do);
        }else if(position==1){
            return context.getString(R.string.shop);
        }else if (position==2){
            return context.getString(R.string.dine);
        }else if (position==3){
            return context.getString(R.string.stay);
        }else{
            return context.getString(R.string.news_stories);
        }
    }
}
