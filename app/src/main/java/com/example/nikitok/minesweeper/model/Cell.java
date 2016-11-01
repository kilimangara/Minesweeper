package com.example.nikitok.minesweeper.model;


public class Cell {
    private boolean isOpened=false;
    private TypeOfCell type=TypeOfCell.Empty;
    private int number=0;

   /* public Cell(){
        isOpened = false;
        number = 0;
        type = TypeOfCell.Empty;
    }*/

    public void setBomb(){
        type = TypeOfCell.Bomb;
    }
    public TypeOfCell getType(){
        return type;
    }
    public void openCell(){
        isOpened = true;
    }
    public int getNumber(){
        return number;
    }
    public void iterNumb(){
        number++;
    }
    public boolean isBomb(){
        return type==TypeOfCell.Bomb;
    }
    public boolean isOpened(){
        return isOpened;
    }
    public void closeCell(){
        isOpened= false;
    }


}
