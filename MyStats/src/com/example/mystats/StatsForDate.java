package com.example.mystats;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.example.mystats.StatsDB;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatsForDate extends Activity {
	
	private static Button btnChangeDate;
	private static Button btnChangeTime;
	private static Button btnSetLaps;
	private TextView tvUpdateDist;
	private int year, month, day;
	private StatsDB statsDbHelper;
	private SQLiteDatabase mDB = null;
	private SimpleCursorAdapter mAdapter;
	private Date date = null;
	private int lapTime = 0;
	private int totalDist = 0;
	private int numLaps = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statentry);
		
		btnChangeDate = (Button) findViewById(R.id.buttonDate);
		btnChangeTime = (Button) findViewById(R.id.buttonTime);
		btnSetLaps = (Button) findViewById(R.id.buttonLaps);
		
		// Create a new DatabaseHelper
		statsDbHelper = new StatsDB(this);
		
		// Get the underlying database for writing
		mDB = statsDbHelper.getWritableDatabase();
		
		// start with an empty database
		clearAll();
		
		//set default stats
		setCurrentDate();
		setDefaultLaps();
		setDefaultDistance();
		setDefaultTime();
		
//		addListenerOnDateButton();
//		addListenerOnTimeButton();

	}
	
	public class DatePickerFragment extends DialogFragment implements
											DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog (Bundle savedInstanceState) {
	        // Use the current date as the default date in the picker
	        final Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);
	        
	        return new DatePickerDialog(getActivity(), this, year, month, day);
			
		}
		
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			// set selected date into textview
			btnChangeDate.setText(new StringBuilder()
					.append(selectedDay).append("-").append(selectedMonth + 1).append("-").append(selectedYear)
					.append(" "));
			
			date = new Date (selectedYear - 1900, selectedMonth, selectedDay);
			//dateString = DateFormat.format("dd-MM-yyyy", date).toString();
						
	    }
	}
	
	public class NumberPickerFragment extends DialogFragment implements
											NumberPicker.OnValueChangeListener {
				
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Context c = getActivity().getApplicationContext();
			// create an alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), 2); //THEME_HOLO_DARK
			//create a number picker
			final NumberPicker np = new NumberPicker(c);
			
	        np.setMinValue(1);
	        np.setMaxValue(100);
	        np.setWrapSelectorWheel(true);
	        	               
			//LayoutInflater  li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
			//View view = li.inflate(R.layout.num_picker, null);
		    // inform the dialog it has a custom View
		    builder.setView(np);
		    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		        	btnSetLaps.setText(Integer.toString(np.getValue()));
		        	numLaps = np.getValue();
					totalDist = numLaps*50;
					updateDistance(totalDist);
		          }
		        });

		    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialog, int whichButton) {
		            // Cancel.
		          }
		        });
		    
		    return builder.create();
		}

		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			
// what do i do with this function???
			
		}
	}
		
	public class TimePickerFragment extends DialogFragment implements 
													NumberPicker.OnValueChangeListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Context c = getActivity().getApplicationContext();
			
			// create an alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), 2); //THEME_HOLO_DARK
	
			LayoutInflater  li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
			View view = li.inflate(R.layout.num_picker, null);
		    // inform the dialog it has a custom View
		    builder.setView(view);
		    
			//create a number picker
		    final NumberPicker npm = (NumberPicker) view.findViewById(R.id.numberPickerMin);
			final NumberPicker nps = (NumberPicker) view.findViewById(R.id.numberPickerSec);
			// set the minutes
	        npm.setMinValue(0);
	        npm.setMaxValue(60);
	        npm.setWrapSelectorWheel(true);
	        //set the seconds
	        nps.setMinValue(0);
	        nps.setMaxValue(60);
	        nps.setWrapSelectorWheel(true);
	        	        	    
		    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		        	btnChangeTime.setText(new StringBuilder()
					.append(Integer.toString(npm.getValue())).append(":").append(Integer.toString(nps.getValue()))
					.append(" "));
		        	
				    // get the laptime in seconds
				    lapTime = (npm.getValue() * 60) + nps.getValue();
		          }
		        });

		    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialog, int whichButton) {
		            // Cancel.
		          }
		        });
		    
		    return builder.create();
		}

		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			
// what do i do with this function???
			
		}
				
	}
	
	public void setCurrentDate() {
			
			btnChangeDate = (Button) findViewById(R.id.buttonDate);
			
			Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			
			// set current date into textview
			btnChangeDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
			.append(day).append("-").append(month + 1).append("-")
				.append(year).append(" "));
			
			date = new Date (year - 1900, month, day);
			
	}
	
	public void setDefaultTime() {
		btnChangeTime = (Button) findViewById(R.id.buttonTime);
		
		btnChangeTime.setText("0:0");
	}
	
	public void updateDistance(int dist) {
		tvUpdateDist = (TextView) findViewById(R.id.tvUpdateDistance);
		tvUpdateDist.setText(Integer.toString(dist));
	}
	

	public void setDefaultLaps() {
		btnSetLaps = (Button) findViewById(R.id.buttonLaps);
		btnSetLaps.setText("0");
			
	}
		
	public void setDefaultDistance() {
		tvUpdateDist = (TextView) findViewById(R.id.tvUpdateDistance);
		tvUpdateDist.setText("0");
	}
		
		public void showDatePickerDialog (View v) {
			DialogFragment newDateFragment = new DatePickerFragment();
			newDateFragment.show(getFragmentManager(), "datepicker");
			
		}
		
		public void showNumPickerDialog (View v) {
			DialogFragment newNumFragment = new NumberPickerFragment();
			newNumFragment.show(getFragmentManager(), "numberpicker");
			
		}
		
		public void showTimePickerDialog(View v) {
			DialogFragment newTimeFragment = new TimePickerFragment();
			newTimeFragment.show(getFragmentManager(), "timepickerfragment");
			
		}
		
		//save the entered data
		public void saveRecord(View v) {
			insertTiming();
			// create sqlite entry
			Toast.makeText(getApplicationContext(), "Data saved!", Toast.LENGTH_SHORT).show();
			
			// temporary check to verify data is written correctly
			readData();
			
			// Create a new intent and send the date back to main activity
			Intent dateSendIntent = new Intent(StatsForDate.this, MainActivity.class);
			dateSendIntent.putExtra("saveddate", date);
						
			// set Activity's result with result code RESULT_OK
			setResult(RESULT_OK, dateSendIntent);
			
			//finish the activity
			finish();
		}
		
		//Cancel the data entry 
		public void cancelDataEntry(View v) {
			// create sqlite entry
			Toast.makeText(getApplicationContext(), "Data entry cancelled!", Toast.LENGTH_SHORT).show();
			readData();
			
			//finish the activity
			finish();
		}
		
		public void insertTiming() {
			ContentValues values = new ContentValues();
			
			values.put(StatsDB.DATE, date.toString());
			values.put(StatsDB.SET1_LAPS, numLaps);
			values.put(StatsDB.SET1_DISTANCE, totalDist);
			values.put(StatsDB.SET1_TIME, lapTime);
			
			values.put(StatsDB.SET2_LAPS, 0);
			values.put(StatsDB.SET2_DISTANCE, 0);
			values.put(StatsDB.SET2_TIME, 0);
				
			values.put(StatsDB.CSS, 0);
			
			mDB.insert(StatsDB.TIMINGS_TABLE_NAME, null, values);
			
			values.clear();
		}
		
		public void readData() {
			// 1. Get the underlying database for reading
			mDB = statsDbHelper.getReadableDatabase();
			
		    // 2. build query
		    Cursor cursor =
		    		mDB.query(StatsDB.TIMINGS_TABLE_NAME, // a. table
		            		StatsDB.columns, // b. column names
		            		" date = ?", // c. selections
		            		new String[] { date.toString() }, // d. selections args
		            		null, // e. group by
		            		null, // f. having
		            		null, // g. order by
		            		null); // h. limit
		    
		 // 3. if we got results get the first one
		    if (cursor != null)
	            cursor.moveToFirst();
		    
		    Log.d("Date ", cursor.getString(0));
		    Log.d("Laps" , cursor.getString(1));
		    Log.d("Distance", cursor.getString(2));
		    Log.d("Time ", cursor.getString(3));
		    Log.d("CSS", cursor.getString(4));
		}
		
		// Delete all records
		private void clearAll() {

			mDB.delete(StatsDB.TIMINGS_TABLE_NAME, null, null);

		}
		
}
