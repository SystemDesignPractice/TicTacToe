package org.example.model;

import lombok.Data;
import org.example.exception.GameOverException;
import org.example.exception.InvalidInputException;
import org.example.service.botPlayingStrategy.BotPlayingStrategyFactory;

@Data
public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;

    public Bot(String botName, PlayerType playerType, char symbol, BotDifficultyLevel botDifficultyLevel) {
        super(botName,playerType,symbol);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board, Player player) throws InvalidInputException, GameOverException {
        return BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.EASY).makeMove(board,player);
    }
}
