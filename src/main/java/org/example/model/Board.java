package org.example.model;

import lombok.Data;

import java.util.*;

@Data
public class Board {
    int size;
    List<List<Cell>> board;

    public Board(int size){
        this.size = size;
        this.board = new ArrayList<>();
        for(int i = 0;i<size;i++){
            this.board.add(new ArrayList<>());
            for(int j = 0;j<size;j++){
                Cell cell = new Cell(i,j);
                this.board.get(i).add(cell);
            }
        }
    }

    public Board(Board board){

        this.size = board.size;
        this.board = new ArrayList<>();

        for(int i = 0;i<size;i++){
            this.board.add(new ArrayList<>());
            for(int j = 0;j<size;j++){
                Cell a = new Cell(board.getBoard().get(i).get(j));
                this.board.get(i).add(a);
            }
        }
    }

    public void printBoard() {
        for(int i = 0;i<size;i++){
            List<Cell> cells = board.get(i);
            for(Cell cell : cells){
                cell.display();
            }
            System.out.println();
        }
    }
}
