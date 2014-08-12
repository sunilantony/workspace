package com.example.mystats;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * Create my own Array Adapter which formats the date as dd-MM-yyyy
*/
public class MyAdapter extends ArrayAdapter <Date> {
	 private final Context context;
	 private final ArrayList<Date> values;
	
	  public MyAdapter(Context context, ArrayList<Date> values) {
		    super(context, R.layout.date_list_item, values);
		    this.context = context;
		    this.values = values;
		  }
	  @Override
	  public View getView (int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    	//inflate the row view
		        View rowView = inflater.inflate(R.layout.date_list_item, parent, false);
		        TextView textView = (TextView) rowView.findViewById(R.id.dateitemtext);
		        //format the row view as per dd-MM-yyyy
		        textView.setText(DateFormat.format("dd-MM-yyyy", values.get(position)));
		        
		        return rowView;
	  }
	  
}
