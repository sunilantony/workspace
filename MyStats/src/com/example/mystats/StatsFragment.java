package com.example.mystats;

import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatsFragment extends Fragment {
	private static final String TAG = "StatsFragment";

	private TextView mStatsView = null;
	private int mCurrIdx = -1;
	private int mDateArrLen;
	private StatsDB statsDbHelper;
	private SQLiteDatabase mDB = null;

	int getShownIndex() {
		return mCurrIdx;
	}

	void showIndex(int newIndex) {
		if (newIndex < 0 || newIndex > mDateArrLen)
			return;
		
		mCurrIdx = newIndex;
		Date date = MainActivity.dateList.get(mCurrIdx);
		// 2. build query
		Cursor cursor =
		  		mDB.query(StatsDB.TIMINGS_TABLE_NAME, // a. table
		            		StatsDB.columns, // b. column names
		            		" date = ?", // c. selections
		            		new String[] { Long.toString(date.getTime()) }, // d. selections args
		            		null, // e. group by
		            		null, // f. having
		            		null, // g. order by
		            		null); // h. limit
		   
		// 3. if we got results get the first one
	    if (cursor != null)
	    	cursor.moveToFirst();
           	    
		mStatsView.setText("Laps1:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET1_LAPS)) + System.getProperty("line.separator") + 
							"Distance1:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET1_DISTANCE)) + System.getProperty("line.separator") +
							"Time1:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET1_TIME)) + System.getProperty("line.separator") +
							"Laps2:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET2_LAPS)) + System.getProperty("line.separator") + 
							"Distance2:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET2_DISTANCE)) + System.getProperty("line.separator") +
							"Time2:" + cursor.getString(cursor.getColumnIndex(StatsDB.SET2_TIME)) + System.getProperty("line.separator") +
							"CSS:" + cursor.getString(cursor.getColumnIndex(StatsDB.CSS)) + System.getProperty("line.separator"));
		
		cursor.close();
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

		return inflater.inflate(R.layout.stats_fragment,
				container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		mStatsView = (TextView) getActivity().findViewById(R.id.statsView);

		// Create a new DatabaseHelper
		statsDbHelper = new StatsDB(getActivity());
				    
	}
	
	@Override
	public void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();

	}
	
	@Override
	public void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
		 
		// get the updated array size
		mDateArrLen = MainActivity.dateList.size();
		// 1. Get the underlying database for reading
		mDB = statsDbHelper.getReadableDatabase();
	}

	
	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
		
		// close the DB
		statsDbHelper.close();

	}

	@Override
	public void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}
	
	@Override
	public void onDetach() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
		super.onDetach();

	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
		super.onDestroyView();
	}

}
