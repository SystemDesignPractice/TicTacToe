package org.example;

import org.example.controller.GameController;
import org.example.exception.GameOverException;
import org.example.exception.InvalidInputException;
import org.example.model.*;
import org.example.service.botPlayingStrategy.BotPlayingStrategy;
import org.example.service.botPlayingStrategy.BotPlayingStrategyFactory;

import java.util.*;

public class TicTacToeGame {
    public static void main(String[] args) throws InvalidInputException, GameOverException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Enter the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in game");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension-1;

        if(isBotPresent.equalsIgnoreCase("y")){
            iteratorNumber--;
        }

        for(int i = 0;i<iteratorNumber;i++){
            System.out.println("What is the name of the player, number: "+i);
            String playerName = sc.next();

            System.out.println("What is the symbol of the player, number: "+i);
            String symbol = sc.next();

            players.add(new Player(playerName, PlayerType.HUMAN,symbol.charAt(0)));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){
            System.out.println("What is the name of the Bot");
            String botName = sc.next();

            System.out.println("What is the symbol of the bot");
            String symbol = sc.next();
            //taking dimension as id, becuase player id will not exceed dimernsion

            BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY;
            BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
            players.add(new Bot(botName, PlayerType.BOT,symbol.charAt(0),botDifficultyLevel));
        }

        // Randomizes the list of player, so the order of playing is random
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension,players);

        int playerIndex = -1;

        while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){

            System.out.println("Current board status: " );
            gameController.printBoard(game);

            playerIndex++;
            playerIndex = playerIndex % players.size();
            Player player = players.get(playerIndex);

            Move lastMove = gameController.makeMove(game,player);

            if(lastMove != null && player.getPlayerType() != PlayerType.BOT) {
                System.out.println("Do you want to undo your move? Y/N");
                String isUndoRequired = sc.next();
                if (isUndoRequired.equalsIgnoreCase("Y")) {
                    gameController.undoMove(game);
                    playerIndex--;
                    continue;
                }
            }

            if(lastMove != null){
                Player winner = gameController.checkWinner(game,lastMove);

                if(winner != null){
                    System.out.println("The winner is :"+ winner.getName());
                    break;
                }
            }
            else {
                playerIndex--;
            }


        }
        System.out.println("Final board states: ");
        gameController.printBoard(game);
        System.out.println("DO YOU WANT A REPLAY Y/N");
        String isReplayRequired = sc.next();
        if(isReplayRequired.equalsIgnoreCase("Y")){
            gameController.replayGame(game);
        }
    }

}