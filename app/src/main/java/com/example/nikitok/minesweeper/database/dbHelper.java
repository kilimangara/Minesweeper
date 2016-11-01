package com.example.nikitok.minesweeper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class dbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="Minesweeper";

    public static final String TASK_TABLE ="players_table";

    public static final String PLAYERS_NAME_COLUMN = "player_name";
    public static final String PLAYER_SCORE_COLUMN = "player_score";

    private static final String PLAYERS_TABLE_SCRIPT="CREATE TABLE "+TASK_TABLE+" ("+
            BaseColumns._ID+" INTEGER PRIMARY KEY, "+ PLAYERS_NAME_COLUMN+" TEXT NOT NULL, "+
            PLAYER_SCORE_COLUMN+" INTEGER);";
    public static final String SELECTION_NAME = PLAYERS_NAME_COLUMN + " = ?";

    private dbQueryManager queryManager;
    private dbUpdateManager updateManager;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        queryManager = new dbQueryManager(getReadableDatabase());
        updateManager = new dbUpdateManager(getWritableDatabase());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PLAYERS_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TASK_TABLE);
        onCreate(db);
    }
    public void saveScore(String name, Integer score){

        ContentValues newValues = new ContentValues();

        newValues.put(PLAYERS_NAME_COLUMN, name);
        newValues.put(PLAYER_SCORE_COLUMN, score);
        getWritableDatabase().insert(TASK_TABLE, null, newValues);

    }
    public dbQueryManager getQueryManager(){
        return queryManager;
    }
    public dbUpdateManager getUpdateManager(){
        return  updateManager;
    }

}
