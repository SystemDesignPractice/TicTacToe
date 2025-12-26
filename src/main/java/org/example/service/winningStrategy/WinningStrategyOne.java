package org.example.service.winningStrategy;

import org.example.model.Board;
import org.example.model.Cell;
import org.example.model.Move;
import org.example.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WinningStrategyOne implements WinningStrategy{

    private int dimension;
    private List<HashMap<Character,Integer>> rowHashmaps;
    private List<HashMap<Character,Integer>> colHashmaps;
    private HashMap<Character,Integer> topLeftHashmap;
    private HashMap<Character,Integer> bottomRightHashmap;
    private HashMap<Character,Integer> cornerHashmap;

    public WinningStrategyOne(int dimension){
        rowHashmaps = new ArrayList<>();
        colHashmaps = new ArrayList<>();
        topLeftHashmap = new HashMap<>();
        bottomRightHashmap = new HashMap<>();
        cornerHashmap  = new HashMap<>();

        for(int i = 0;i<dimension;i++){
            this.rowHashmaps.add(new HashMap<>());
            this.colHashmaps.add(new HashMap<>());
        }

        this.dimension = dimension;
    }


    public boolean isRowWin(int row, Character symbol){
        HashMap<Character,Integer> hm = rowHashmaps.get(row);
        hm.putIfAbsent(symbol,0);
        hm.put(symbol,hm.get(symbol) + 1);
        return hm.get(symbol) == this.dimension;
    }

    public boolean isColWin(int col, Character symbol){
        HashMap<Character,Integer> hm = colHashmaps.get(col);
        hm.putIfAbsent(symbol,0);
        hm.put(symbol,hm.get(symbol) + 1);
        return hm.get(symbol) == this.dimension;
    }

    public boolean isTopLeftWin(Character symbol){
        topLeftHashmap.putIfAbsent(symbol,0);
        topLeftHashmap.put(symbol,topLeftHashmap.get(symbol) + 1);
        return topLeftHashmap.get(symbol) == this.dimension;
    }

    public boolean isBottomRightWin(Character symbol){
        bottomRightHashmap.putIfAbsent(symbol,0);
        bottomRightHashmap.put(symbol,bottomRightHashmap.get(symbol)+1);
        return bottomRightHashmap.get(symbol) == this.dimension;
    }

    public boolean isCornerWin(Character symbol){
        cornerHashmap.putIfAbsent(symbol,0);
        cornerHashmap.put(symbol,cornerHashmap.get(symbol)+1);
        return cornerHashmap.get(symbol) == this.dimension;
    }

    public boolean isTopLeft(int row,int col){
        return row == col;
    }

    public boolean isBottomRight(int row,int col){
        return (row+col) == dimension-1;
    }

    public boolean isCorner(int row,int col){

        if(row == 0 || row == dimension-1){
            return col == 0 || col == dimension-1;
        }
        return false;
    }

    @Override
    public Player checkWinner(Move lastMove) {
        Cell cell = lastMove.getCell();
        Player player = lastMove.getPlayer();

        if(isRowWin(cell.getRow(),player.getSymbol())){
            return player;
        }
        if(isColWin(cell.getCol(),player.getSymbol())){
            return player;
        }
        if(isTopLeft(cell.getRow(),cell.getCol()) && isTopLeftWin(player.getSymbol())){
            return player;
        }
        if(isBottomRight(cell.getRow(),cell.getCol()) && isBottomRightWin(player.getSymbol())){
            return player;
        }
        if(isCorner(cell.getRow(),cell.getCol()) && isCornerWin(player.getSymbol())){
            return player;
        }

        return null;
    }

    public void undoMove(Move move){
        Cell cell = move.getCell();
        Player player = move.getPlayer();
        int row = cell.getRow();
        int col = cell.getCol();
        char symbol = player.getSymbol();

        // Update rowHashmap
        HashMap<Character,Integer> rowHm = rowHashmaps.get(row);
        rowHm.put(symbol,rowHm.get(symbol) - 1);

        // Update colHashmap
        HashMap<Character,Integer> colHm = colHashmaps.get(col);
        colHm.put(symbol,colHm.get(symbol) - 1);

        if(isTopLeft(row,col)){
            topLeftHashmap.put(symbol,topLeftHashmap.get(symbol)-1);
        }

        if(isBottomRight(row,col)){
            bottomRightHashmap.put(symbol,bottomRightHashmap.get(symbol)-1);
        }
        if(isCorner(row,col)){
            cornerHashmap.put(symbol,cornerHashmap.get(symbol)-1);
        }

    }
}
