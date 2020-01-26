package com.dlb.fireEmblem.unit;

import com.dlb.fireEmblem.map.GameMap;
import com.dlb.fireEmblem.map.model.WaterTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UnitMovementCalculatorTest
{
    private static final Point STARTING_TILE = new Point(0, 0);

    private UnitMovementCalculator movementCalculator;

    @BeforeEach
    public void givenMovementCalculatorOn20x20Map()
    {
        movementCalculator = new UnitMovementCalculator();
        movementCalculator.setGameMap(new GameMap(20, 20));
    }

    @Test
    public void movingAnUnitToAReachableTile_theMoveIsAccepted()
    {
        final Point newTile = new Point(1, 1);

        assertThat(movementCalculator.isValidMove(STARTING_TILE, newTile), is(true));
    }

    @Test
    public void movingAnUnitToATileTooFar_theMoveIsRefused()
    {
        final Point farAwayTile = new Point(6, 0);

        assertThat(movementCalculator.isValidMove(STARTING_TILE, farAwayTile), is(false));
    }

    @Test
    public void movingAnUnitOnAWaterTile_theMoveIsRefused()
    {
        final GameMap gameMap = getGameMapWithWater();
        movementCalculator.setGameMap(gameMap);

        assertThat(movementCalculator.isValidMove(STARTING_TILE, new Point(1, 0)), is(false));
    }

    private GameMap getGameMapWithWater()
    {
        final GameMap gameMap = new GameMap(20, 20);
        gameMap.addTile(1, 0, new WaterTile());

        return gameMap;
    }
}
