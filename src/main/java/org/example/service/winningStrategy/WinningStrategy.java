package org.example.service.winningStrategy;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.Player;

public interface WinningStrategy {
    Player checkWinner(Move lastMove);

    void undoMove(Move move);
}
