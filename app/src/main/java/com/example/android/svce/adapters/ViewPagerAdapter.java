package com.example.android.svce.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.svce.activities.MyInfoFragment;
import com.example.android.svce.R;
import com.example.android.svce.activities.MyIdeasFragment;
import com.example.android.svce.activities.SavedIdeasFragment;

/**
 * Created by jennifernghinguyen on 2/24/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    String[] pageItems;
    public ViewPagerAdapter(FragmentManager fm, Context _context) {
        super(fm);
        context = _context;
        pageItems = context.getResources().getStringArray(R.array.user_tab_title);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MyInfoFragment();
        } else if (position == 1) {
            return new MyIdeasFragment();
        } else  {
            return new SavedIdeasFragment();
        }
    }

    @Override
    public int getCount() {
        return pageItems.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return pageItems[0];
        } else if (position == 1) {
            return pageItems[1];
        } else
            return pageItems[2];
        }


}
