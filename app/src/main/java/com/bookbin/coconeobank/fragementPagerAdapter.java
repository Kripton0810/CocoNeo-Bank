package com.bookbin.coconeobank;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class fragementPagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public fragementPagerAdapter(@NonNull FragmentManager fm, int behavior,int tabs) {
        super(fm, behavior);
        this.tabsNumber=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new home_loan_fragement();
            case 1:
                return new retire_fragement();
            case 2:
                return new curr_fragement();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
