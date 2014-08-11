package com.example.mystats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsDB extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 2;
    static final String TIMINGS_TABLE_NAME = "timings";
    static final String DATE = "date";
    static final String SET1_LAPS = "laps1";
    static final String SET1_DISTANCE = "dist1";
    static final String SET1_TIME = "time1";
    static final String SET2_LAPS = "laps2";
    static final String SET2_DISTANCE = "dist2";
    static final String SET2_TIME = "time2";
    static final String CSS = "css";
    
    final static String[] columns = { DATE, SET1_LAPS, SET1_DISTANCE, SET1_TIME, SET2_LAPS,
    									SET2_DISTANCE, SET2_TIME, CSS };
    
    private static final String TIMINGS_TABLE_CREATE =
                "CREATE TABLE " + TIMINGS_TABLE_NAME + " (" +
                DATE + " INT PRIMARY KEY, " +
                SET1_LAPS + " INT," +
                SET1_DISTANCE + " INT, " +
                SET1_TIME + " TEXT, " +
                SET2_LAPS + " INT, " +
                SET2_DISTANCE + " INT, " +
                SET2_TIME + " TEXT, " +
                CSS + " TEXT );";

    StatsDB(Context context) {
        super(context, TIMINGS_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TIMINGS_TABLE_CREATE);
    }
    
    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

}
