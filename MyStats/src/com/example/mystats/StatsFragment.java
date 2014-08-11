package com.example.mystats;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatsFragment extends Fragment {
	private static final String TAG = "QuotesFragment";

	private TextView mStatsView = null;
	private int mCurrIdx = -1;
	private int mQuoteArrLen;

	int getShownIndex() {
		return mCurrIdx;
	}

	void showIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		/*mStatsView.setText(MainActivity.StatsDB.date.toString);
		mStatsView.setText(MainActivity.StatsDB.lap1.toString);
		mStatsView.setText(MainActivity.StatsDB.dist1.toString);
		mStatsView.setText(MainActivity.StatsDB.time1.toString);
		... */
		
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
		// why is this length required???
		//mQuoteArrLen = MainActivity.mQuoteArray.length;
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
