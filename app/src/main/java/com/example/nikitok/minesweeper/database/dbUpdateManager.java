package com.example.nikitok.minesweeper.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class dbUpdateManager {

    SQLiteDatabase db;

    public dbUpdateManager(SQLiteDatabase db){
        this.db = db;
    }

    public void score(String name, Integer score){
        ContentValues contentValues = new ContentValues();

        contentValues.put(dbHelper.PLAYER_SCORE_COLUMN,score);

        db.update(dbHelper.TASK_TABLE, contentValues,dbHelper.SELECTION_NAME, new String[]{name});
    }


}
