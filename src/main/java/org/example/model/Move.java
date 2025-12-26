package org.example.model;

import lombok.Data;

@Data
public class Move {
    Cell cell;
    Player player;

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
}
