package com.example.angelo.doorbelliot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by angelo on 08/05/17.
 */

class SectionsPagerAdapter extends FragmentPagerAdapter {

    Fragment uno, due;

    public SectionsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(uno==null) uno=new NewConnection();
                return uno;
            case 1:
                if(due==null) due=new Cronologia();
                return due;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Connetti";
            case 1:
                return "Cronologia";
            default:
                break;
        }
        return null;
    }
}
