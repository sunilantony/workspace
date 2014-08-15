package com.example.mystats;

import java.util.Date;

import com.example.mystats.MainActivity;
import com.example.mystats.MyAdapter;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.mystats.StatsDB;

public class DateListFragment extends ListFragment {
	
	private static final String TAG = "DateListFragment";
	private ListSelectionListener mListener = null;
	private MyAdapter dateAdapter;
	private StatsDB statsDbHelper;
	private SQLiteDatabase mDB = null;
	
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		getListView().setItemChecked(pos, true);
		mListener.onListSelection(pos);
	}

	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
		
		try {
			mListener = (ListSelectionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
		
		statsDbHelper = new StatsDB(getActivity());
		
		// fill the list with current dates in the DB
		fillList();
				
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
				
		return super.onCreateView(inflater, container, savedInstanceState);
		

	}


	@Override
	public void onActivityCreated(Bundle savedState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedState);
		
		//create the adapter to hold the array of dates
		dateAdapter = new MyAdapter(getActivity(), MainActivity.dateList);
	    //attach the array adapter to the list view
		setListAdapter(dateAdapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
	}
	
	public void fillList() {
		// Get the underlying database for reading
		mDB = statsDbHelper.getReadableDatabase();
		
		// build query
		Cursor cursor =
			mDB.query(StatsDB.TIMINGS_TABLE_NAME, // a. table
		        		StatsDB.columns, // b. column names
		        		null, // c. selections
				        null, // d. selections args
				        null, // e. group by
				        null, // f. having
				        null, // g. order by
				        null); // h. limit
				    
		// if we got results get the first one
		    if (cursor != null)
		            cursor.moveToFirst();

	    // loop through the queried list and add date into dateList
		    while (!cursor.isAfterLast()) {
		    	Log.d("Date ", cursor.getString(cursor.getColumnIndex(StatsDB.DATE)));
		    	Date tmpDate = new Date(cursor.getLong(cursor.getColumnIndex(StatsDB.DATE)));
		    	Log.i(TAG,"Date received is " + tmpDate);
		    	MainActivity.dateList.add(tmpDate);
		        cursor.moveToNext();
		      }
	    cursor.close();
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
		dateAdapter.notifyDataSetChanged();
		
	}

	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
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
