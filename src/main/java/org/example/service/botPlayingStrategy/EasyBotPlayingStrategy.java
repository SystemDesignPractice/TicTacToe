package org.example.service.botPlayingStrategy;

import org.example.exception.GameOverException;
import org.example.model.*;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player player) throws GameOverException {
        int dimension = board.getSize();

        for(int i = 0;i<dimension;i++){
            List<Cell> row = board.getBoard().get(i);
            for(int j = 0;j<dimension;j++){
                if(row.get(j).getCellState() == CellState.EMPTY){
                    board.getBoard().get(i).get(j).setCellState(CellState.FILLED);
                    board.getBoard().get(i).get(j).setPlayer(player);
                    return new Move(new Cell(i,j,player,CellState.FILLED),player);
                }
            }
        }

        throw new GameOverException("Game is over");
    }
}
