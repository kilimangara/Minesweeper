package com.example.nikitok.minesweeper.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nikitok.minesweeper.model.Player;

import java.util.ArrayList;
import java.util.List;


public class dbQueryManager {
    private SQLiteDatabase db;
    public dbQueryManager(SQLiteDatabase db){
        this.db = db;
    }
    public List<Player> getPlayers(String selection, String[] selectionargs, String orderBy){
        List<Player> list = new ArrayList<>();
        int i=0;
        Cursor cursor = db.query(dbHelper.TASK_TABLE, null,selection, selectionargs,null, null, orderBy);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(dbHelper.PLAYERS_NAME_COLUMN));
                Integer score = cursor.getInt(cursor.getColumnIndex(dbHelper.PLAYER_SCORE_COLUMN));
                Player player = new Player(name, score);
                i++;
                list.add(player);

            }while(cursor.moveToNext()&i<10);

        }
        cursor.close();
        return list;
    }
    public Player getPlayer(String name){
        Player player = null;

        Cursor cursor = db.query(dbHelper.TASK_TABLE, null, dbHelper.SELECTION_NAME, new String[]{name},null, null, null);
        if(cursor.moveToFirst()){
            Integer score = cursor.getInt(cursor.getColumnIndex(dbHelper.PLAYER_SCORE_COLUMN));
            player = new Player(name, score);
        }
        cursor.close();
        return player;
    }

}
