package com.boss.viewpagertablayout.PagerAdapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.boss.viewpagertablayout.Fragment.FoodFragment;
import com.boss.viewpagertablayout.Fragment.GeneralFragment;
import com.boss.viewpagertablayout.Fragment.NatureFragment;
import com.boss.viewpagertablayout.Fragment.PlacesFragment;
import com.boss.viewpagertablayout.R;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GeneralFragment();
        } else if (position == 1) {
            return new PlacesFragment();
        } else if (position == 2) {
            return new FoodFragment();
        } else {
            return new NatureFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_usefulinfo);
            case 1:
                return mContext.getString(R.string.category_places);
            case 2:
                return mContext.getString(R.string.category_food);
            case 3:
                return mContext.getString(R.string.category_nature);
            default:
                return null;
        }
    }

}
