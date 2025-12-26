package org.example.service.botPlayingStrategy;

import org.example.exception.GameOverException;
import org.example.model.Board;
import org.example.model.Move;
import org.example.model.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board,Player player) throws GameOverException;
}
