package com.example.nikitok.minesweeper;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.nikitok.minesweeper.adapter.GridViewAdapter;
import com.example.nikitok.minesweeper.database.dbHelper;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NameDialog.NameSetListener, GridViewAdapter.OnEndGameListener,EndGameDialog.ButtonListener {

    private String name;
    private dbHelper dbHelper;
    private   Random r;
    private GridView gridView;
    private GridViewAdapter adapter;
    private DialogFragment fragment;

    public static final int BUTTON_NEW_GAME=0;
    public static final int BUTTON_RESTART_GAME=1;
    public static final int BUTTON_FINISH_GAME=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new dbHelper(this);
        r = new Random();
        DialogFragment fragment1 = new NameDialog();
        fragment1.show(getFragmentManager(), "Name");
        gridView = (GridView) findViewById(R.id.gridView);
        newGame();

    }

    public void newGame(){
        int x = r.nextInt(10)+10;
        int y= r.nextInt(10)+10;
        gridView.setNumColumns(x);
        adapter = new GridViewAdapter(this, y, x);
        gridView.setAdapter(adapter);
    }

    @Override
    public void nameEntered(String name) {
        this.name=name;
    }

    @Override
    public void gameLost(int score) {
        if(dbHelper.getQueryManager().getPlayer(name)==null){
            dbHelper.saveScore(name, score);
        }
        else{
            dbHelper.getUpdateManager().score(name, score);
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", "You lose");
        fragment = new EndGameDialog();
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "endgame");

    }

    @Override
    public void gameWon(int score) {
        if(dbHelper.getQueryManager().getPlayer(name)==null){
            dbHelper.saveScore(name, score);
        }
        else{
            dbHelper.getUpdateManager().score(name, score);
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", "You won");
        fragment = new EndGameDialog();
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "endgame");
    }

    @Override
    public void buttonPressed(int button) {
        fragment.dismiss();
            switch (button){
                case BUTTON_NEW_GAME:
                    newGame();
                    break;
                case BUTTON_RESTART_GAME:
                    adapter.refreshMap();
                    break;
                case BUTTON_FINISH_GAME:
                    finish();
                    break;

            }
    }
}
