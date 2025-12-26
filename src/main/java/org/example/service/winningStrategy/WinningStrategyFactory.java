package org.example.service.winningStrategy;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(WinningStrategies strategy,int dimension){
        return switch (strategy){
            case STRATEGY1 ->  new WinningStrategyOne(dimension);
        };
    }
}
