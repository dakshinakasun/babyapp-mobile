package com.example.babyapp.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.babyapp.Fragment.DailyTabFragment;
import com.example.babyapp.Fragment.MonthlyTabFragment;
import com.example.babyapp.Fragment.WeeklyTabFragment;

public class PostAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public PostAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                DailyTabFragment dailyTabFragment = new DailyTabFragment();
                return dailyTabFragment;
            case 1:
                WeeklyTabFragment weeklyTabFragment = new WeeklyTabFragment();
                return weeklyTabFragment;
            case 2:
                MonthlyTabFragment monthlyTabFragment = new MonthlyTabFragment();
                return monthlyTabFragment;
            default:
                return null;
        }
    }
}
