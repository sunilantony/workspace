package com.example.mystats;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;

import com.example.mystats.DateListFragment.ListSelectionListener; 


public class MainActivity extends Activity implements
							ListSelectionListener	{
	
	static private final int GET_REQUEST_CODE = 1;
	static private final String TAG = "MyStats: Main Activity";
	
	//create the array to store the dates
	public static ArrayList<Date> dateList;
	// create the button and list views
	private Button enterButton;
	
	private final StatsFragment mStatsFragment = new StatsFragment();
	private FragmentManager mFragmentManager;
	private FrameLayout mDateListFrameLayout, mStatsFrameLayout;
	
	private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dateList = new ArrayList<Date> ();
		//Date tmpdate1 = new Date (2014 - 1900, 8, 12);
		//Date tmpdate2 = new Date (2014 - 1900, 8, 7);
		//Date tmpdate3 = new Date (2014 - 1900, 8, 8);
		//Date tmpdate4 = new Date (2014 - 1900, 8, 9);
		//Date tmpdate5 = new Date (2014 - 1900, 8, 10);
		//dateList.add(tmpdate1);
		//dateList.add(tmpdate2);
		//dateList.add(tmpdate3);
		//dateList.add(tmpdate4);
		//dateList.add(tmpdate5);
			
		enterButton = (Button) findViewById(R.id.buttonEnterStats);

		mDateListFrameLayout = (FrameLayout) findViewById(R.id.datelist_fragment_container);
		mStatsFrameLayout = (FrameLayout) findViewById(R.id.stats_fragment_container);

		mFragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.datelist_fragment_container,
				new DateListFragment());
		fragmentTransaction.commit();

		mFragmentManager
				.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {
						setLayout();
					}
				});
		
/*		
  		dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//show the relevant timings and CSS
					Log.i(TAG, "Entered setOnItemClickListener of the Array Adapter()");
				}
		});
*/		
		// handler for button click entering stats
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent enter_stats_for_date = new Intent(MainActivity.this, StatsForDate.class);
				startActivityForResult(enter_stats_for_date,GET_REQUEST_CODE);				
			}

		});
	}
	
	private void setLayout() {
		if (!mStatsFragment.isAdded()) {
			mDateListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
					MATCH_PARENT, MATCH_PARENT));
			mStatsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT));
		} else {
			mDateListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 1f));
			mStatsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 2f));
		}
	}
	
	@Override
	public void onListSelection(int index) {
		if (!mStatsFragment.isAdded()) {
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.stats_fragment_container,
					mStatsFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			mFragmentManager.executePendingTransactions();
		}
		if (mStatsFragment.getShownIndex() != index) {
			mStatsFragment.showIndex(index);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Listview showing the date.
		if (resultCode == RESULT_OK && requestCode == GET_REQUEST_CODE) {
		    if (data.hasExtra("saved_date")) {
		    	long millisec = data.getExtras().getLong("saved_date");
		    	Log.i(TAG,"Date received is " + millisec);
		    	Date tmpDate=new Date(millisec);
		    	dateList.add(tmpDate);
		    	
		    	//debug printing
		    	printDateList();
		    	
		    }
		}
	}
	
	public void printDateList() {
		for (int i = 0; i < dateList.size(); i++)
			Log.i("the Dates are ", i + ". " + dateList.get(i));
	}
	
}
