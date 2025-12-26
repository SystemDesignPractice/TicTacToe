package org.example.model;

import lombok.Data;
import org.example.exception.InvalidInputException;
import org.example.service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class Game {
    Board currentBoard;
    List<Player> players;
    Player currentPlayer;
    Player winner;
    List<Move> moves;
    List<Board> boardStates;
    GameStatus gameStatus;
    WinningStrategy winningStrategy;
    int totalNumberOfFilledCells;

    public Game(Board currentBoard,List<Player> players,WinningStrategy winningStrategy){
        this.currentBoard = currentBoard;
        this.players = players;
        this.winningStrategy = winningStrategy;
        this.moves = new ArrayList<Move>();
        this.boardStates = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder dimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy){
            this.winningStrategy = winningStrategy;
            return this;
        }
        private void validateDimension() throws InvalidInputException{
            if(this.dimension <3 || this.dimension > 10){
                throw  new InvalidInputException("Board size should be between size 3 and 10");
            }
        }

        private void validatePlayerSymbol() throws InvalidInputException{
            HashSet<Character> hs = new HashSet<>();
            for(Player player : this.players){
                hs.add(player.symbol);
            }

            if(hs.size() != this.players.size()){
                throw new InvalidInputException("Duplicate player symbol. Every player should have unique symbol");
            }
        }

        private void validateBotCount() throws InvalidInputException{

            int count = 0;
            for(Player player:this.players){
                if(player.playerType == PlayerType.BOT){
                    count++;
                }
            }

            if(count > 1){
                throw new InvalidInputException("The number of bots allowed is 1.");
            }
        }

        private void validatePlayerCount() throws InvalidInputException{
            if( this.players.size() != (this.dimension - 1)){
                throw new InvalidInputException("Player count should be equal to board size - 1");
            }
        }

        private void validate() throws InvalidInputException {
            validateDimension();
            validatePlayerSymbol();
            validateBotCount();
            validatePlayerCount();
        }

        public Game build() throws InvalidInputException {
            validate();
            return new Game(new Board(this.dimension),this.players,this.winningStrategy);
        }
    }

}
