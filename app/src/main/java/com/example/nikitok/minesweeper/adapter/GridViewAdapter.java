package com.example.nikitok.minesweeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikitok.minesweeper.R;
import com.example.nikitok.minesweeper.model.Cell;

import java.util.Random;


public class GridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private Cell[][] map;
    private int rows;//строки
    private int columns;//колонки
    private int numOfBombs;
    private TextView textView;
    private int points;
    private OnEndGameListener  listener;
    public interface OnEndGameListener{
        void gameLost(int score);
        void gameWon(int score);
    }
    public GridViewAdapter(Context context, int rows, int columns ){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map= new Cell[rows][columns];
        listener = (OnEndGameListener) context;
        this.context =context;
        this.rows= rows;
        this.columns = columns;
        numOfBombs = (rows*columns)/10;
        points=0;
        initmap();
        fillmap();

    }
    private void initmap(){
        for(int i=0;i<rows;i++){
            for(int k=0; k<columns;k++){
                map[i][k]= new Cell();
            }
        }
    }
    private void fillmap(){
        int i=0;
        Random r = new Random();
        while(i<numOfBombs){
            int x=r.nextInt(columns-3)+1;
            int y=r.nextInt(rows-3)+1;
            if(!map[y][x].isBomb()){
                map[y][x].setBomb();
                i++;
                for(int k=y-1;k<=y+1;++k){
                    for(int kk= x-1;kk<=x+1;++kk){
                        map[k][kk].iterNumb();
                    }
                }
            }

        }
    }
    public void refreshMap(){
        for(int i=0;i<rows;i++){
            for(int k=0; k<columns;k++){
                map[i][k].closeCell();
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return rows*columns;
    }

    @Override
    public Object getItem(int position) {
        return map[position/columns][position%columns];
    }
    public Cell getCell(int position){
        return (Cell)getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Cell cell = getCell(position);
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.model_cell, parent, false);
        }
        textView = (TextView) view.findViewById(R.id.button);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCells(position);
                notifyDataSetChanged();
                if (points==rows*columns-numOfBombs){
                    listener.gameWon(points);
                }
            }
        });

        if(cell.isOpened()){
            if(cell.isBomb()){
                textView.setBackground(context.getDrawable(R.drawable.cell_blowed));
                listener.gameLost(points);
            }
            else{
               switch (cell.getNumber()){
                   case 0:
                       textView.setBackground(context.getDrawable(R.drawable.cell_opened));
                       break;
                   case 1:
                       textView.setText("1");
                       textView.setTextColor(context.getResources().getColor(R.color.yellow));
                       break;
                   case 2:
                       textView.setText("2");
                       textView.setTextColor(context.getResources().getColor(R.color.green));
                       break;
                   case 3:
                       textView.setText("3");
                       textView.setTextColor(context.getResources().getColor(R.color.red));
                       break;
                   case 4:
                       textView.setText("4");
                       textView.setTextColor(context.getResources().getColor(R.color.red));
                       break;
                   case 5:
                       textView.setText("5");
                       textView.setTextColor(context.getResources().getColor(R.color.red));
                       break;
                   case 6:
                       textView.setText("6");
                       textView.setTextColor(context.getResources().getColor(R.color.red));
                       break;

               }
            }
        }
        return view;
    }

    private void openCells(int position){
        int x = position%columns;
        int y= position/columns;
            if (!map[y][x].isOpened()) {
                map[y][x].openCell();
                textView.setClickable(false);
                points++;
                if (map[y][x].getNumber() == 0) {
                    for (int i = y - 1; i < y + 2; i++) {
                        for (int k = x - 1; k < x + 2; ++k) {
                            if(k<columns&i<rows&i>-1&k>-1) {
                                openCells(i * columns + k);
                            }
                        }
                    }
                }
            }
    }
}
