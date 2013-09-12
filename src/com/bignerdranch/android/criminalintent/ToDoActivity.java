package com.bignerdranch.android.criminalintent;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class ToDoActivity extends SingleFragmentActivity {
	@Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
            .getSerializableExtra(ToDoFragment.EXTRA_CRIME_ID);
        return ToDoFragment.newInstance(crimeId);
    }
}
