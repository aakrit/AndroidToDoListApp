package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

import android.util.Log;

public class ToDoList {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<ToDoTask> mToDoTasks;
    private ProToDosJSONSerializer mSerializer;

    private static ToDoList sCrimeLab;
    private Context mAppContext;

    private ToDoList(Context appContext) {
        mAppContext = appContext;
        mSerializer = new ProToDosJSONSerializer(mAppContext, FILENAME);

        try {
            mToDoTasks = mSerializer.loadCrimes();
        } catch (Exception e) {
            mToDoTasks = new ArrayList<ToDoTask>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
    }

    public static ToDoList get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new ToDoList(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ToDoTask getCrime(UUID id) {
        for (ToDoTask c : mToDoTasks) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
    
    public void addCrime(ToDoTask c) {
        mToDoTasks.add(c);
        saveCrimes();
    }

    public ArrayList<ToDoTask> getCrimes() {
        return mToDoTasks;
    }

    public void deleteCrime(ToDoTask c) {
        mToDoTasks.remove(c);
        saveCrimes();
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mToDoTasks);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " + e, e);
            return false;
        }
    }
}

