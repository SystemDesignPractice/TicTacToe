package org.example.controller;

import org.example.exception.GameOverException;
import org.example.exception.InvalidInputException;
import org.example.model.*;
import org.example.service.winningStrategy.WinningStrategies;
import org.example.service.winningStrategy.WinningStrategy;
import org.example.service.winningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {
    public Game createGame(int dimensions, List<Player> players) throws InvalidInputException {
        try{
            return Game.builder()
                    .dimension(dimensions)
                    .players(players)
                    .winningStrategy(WinningStrategyFactory.getWinningStrategy(WinningStrategies.STRATEGY1,dimensions))
                    .build();
        }
        catch (Exception e){
            System.out.println("An error occured: "+e.getMessage());
            return null;
        }
    }

    public Move makeMove(Game game, Player player) throws InvalidInputException, GameOverException {
        try{
            Move move = player.makeMove(game.getCurrentBoard(),player);
            game.setTotalNumberOfFilledCells(game.getTotalNumberOfFilledCells()+1);
            updateMoves(game,move);
            updateBoardStates(game);
            updateGameStatus(game);
            return move;
        }
        catch (Exception e){
            System.out.println("An error occured: "+e.getMessage());
        }
        return null;
    }

    private void updateBoardStates(Game game){
        game.getBoardStates().add(new Board(game.getCurrentBoard()));
    }

    private void updateMoves(Game game,Move move){
        game.getMoves().add(move);
    }

    private void updateGameStatus(Game game){
        int size = game.getCurrentBoard().getSize();
        if(game.getTotalNumberOfFilledCells() == size*size){
            game.setGameStatus(GameStatus.DRAW);
        }
    }

    public Player checkWinner(Game game,Move lastMove){
        WinningStrategy winningStrategy = game.getWinningStrategy();
        Player player = winningStrategy.checkWinner(lastMove);
        if(player != null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.WINNER);
        }
        return player;
    }

    public void undoMove(Game game) throws InvalidInputException {
        int moves = game.getBoardStates().size();

        if(moves<1){
            throw new InvalidInputException("Cannot undo anymore");
        }

        List<Board> boardStates = game.getBoardStates();
        boardStates.remove(moves-1);
        game.setBoardStates(boardStates);

        List<Move> moveList = game.getMoves();
        Move removedMove = moveList.remove(moves-1);
        game.setMoves(moveList);

        if(moves == 1){
            game.setCurrentBoard(new Board(game.getPlayers().size()+1));
        }
        else{
            moves = game.getBoardStates().size();
            game.setCurrentBoard(new Board(game.getBoardStates().get(moves-1)));
        }

//        game.getWinningStrategy().undoMove(removedMove);
//
        System.out.println("New board after undo == ");
        game.getCurrentBoard().printBoard();

    }

    public void replayGame(Game game){
        List<Board> boards = game.getBoardStates();
        for(Board board:boards){
            board.printBoard();
        }
    }

    public void printBoard(Game game){
        game.getCurrentBoard().printBoard();
    }

}
