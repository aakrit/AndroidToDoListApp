package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class ToDoPagerActivity extends FragmentActivity {
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        final ArrayList<ToDoTask> toDoTasks = ToDoList.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return toDoTasks.size();
            }
            @Override
            public Fragment getItem(int pos) {
                UUID crimeId =  toDoTasks.get(pos).getId();
                return ToDoFragment.newInstance(crimeId);
            }
        }); 

        UUID crimeId = (UUID)getIntent().getSerializableExtra(ToDoFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < toDoTasks.size(); i++) {
            if (toDoTasks.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            } 
        }
    }
}
