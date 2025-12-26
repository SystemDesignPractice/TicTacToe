package org.example.model;

import lombok.Data;
import org.example.exception.GameOverException;
import org.example.exception.InvalidInputException;

import java.util.Scanner;

@Data
public class Player {
    String id;
    char symbol;
    String name;
    PlayerType playerType;

    public Player(String playerName, PlayerType playerType, char symbol) {
        this.name = playerName;
        this.id = playerName;
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public Player(Player player){
        this.symbol = player.getSymbol();
        this.playerType = player.getPlayerType();
        this.id = player.getId();
        this.name = player.getName();
    }

    public Move makeMove(Board board, Player player) throws InvalidInputException, GameOverException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for your move: ");
        int row = sc.nextInt();
        System.out.println("Enter the col for your move: ");
        int col = sc.nextInt();

        if(row > (board.size-1)){
            throw new InvalidInputException("Row should be less than "+(board.size-1));
        }

        if(col > (board.size-1)){
            throw new InvalidInputException("Col should be less than "+(board.size-1));
        }

        if(board.getBoard().get(row).get(col).cellState == CellState.FILLED){
            throw new InvalidInputException("Cell already filled");
        }

        board.board.get(row).get(col).setPlayer(player);
        board.board.get(row).get(col).setCellState(CellState.FILLED);
        return new Move(new Cell(row,col,player,CellState.FILLED),player);

    }
}
