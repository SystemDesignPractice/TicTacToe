package org.example.model;

import lombok.Data;

@Data
public class Cell {
    int row;
    int col;
    Player player;
    CellState cellState;

    public Cell(int row,int col){
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public Cell(Cell cell){
        this.row = cell.getRow();
        this.col = cell.getCol();
        this.cellState = cell.getCellState();
        this.player = cell.getPlayer();
    }

    public Cell(int row,int col,Player player,CellState cellState){
        this.row = row;
        this.col = col;
        this.player = player;
        this.cellState = cellState;
    }

    public void display() {
        if(player == null ){
            System.out.print("| |");
        }
        else{
            System.out.print("|"+player.getSymbol()+"|");
        }
    }
}
