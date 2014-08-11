package com.example.mystats;

import java.util.Date;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DateListFragment extends ListFragment {
	private static final String TAG = "TitlesFragment";
	private ListSelectionListener mListener = null;

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedState);
		
		setListAdapter(new ArrayAdapter<Date>(getActivity(), R.layout.date_list_item, MainActivity.dateList));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
/*		TextView textView = (TextView) getListView()
				.findViewById(R.id.dateitemtext);
				
//		Log.i(TAG,"First date is " + DateFormat.format("dd-MM-yyyy", tmpdate1));
		Date tmpdate1 = new Date (2014 - 1900, 8, 6);
		textView.setText(DateFormat.format("dd-MM-yyyy", tmpdate1).toString());  */
		
		Date tmpdate1 = new Date (2014 - 1900, 8, 6);
		final LayoutInflater factory = getActivity().getLayoutInflater();
		final View textEntryView =  factory.inflate(R.layout.date_list_item, null);
		TextView textView = (TextView) textEntryView.findViewById(R.id.dateitemtext);
		
		Log.i(TAG,"First date is " + DateFormat.format("dd-MMM-yyyy", tmpdate1));

		textView.setText(DateFormat.format("dd-MMM-yyyy", tmpdate1));
		
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
