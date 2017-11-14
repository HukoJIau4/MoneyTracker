package com.example.lexel.moneytracker;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MainPagerAdapter extends FragmentPagerAdapter {


    private final static int PAGE_EXPENSES = 0;
    private final static int PAGE_INCOMES = 1;
    private final static int PAGE_BALANCE = 2;

    private String[] titles;

    MainPagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);

        titles = resources.getStringArray(R.array.tab_titles);
    }

    @Override
    public Fragment getItem(int position) {



        switch (position) {
            case PAGE_EXPENSES: {
                    return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_EXPENS);
            }

            case PAGE_INCOMES: {
                return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_INCOME);
            }
            case PAGE_BALANCE:
                return BalanceFragment.createBalanceFragment();

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}


