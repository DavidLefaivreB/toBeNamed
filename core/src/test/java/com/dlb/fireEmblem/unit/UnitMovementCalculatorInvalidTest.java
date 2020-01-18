package com.dlb.fireEmblem.unit;

import com.dlb.fireEmblem.map.GameMap;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;

public class UnitMovementCalculatorInvalidTest
{
    private static UnitMovementCalculator movementCalculator;

    @BeforeAll
    static void givenMovementCalculatorWithSimpleMap()
    {
        movementCalculator = new UnitMovementCalculator();
        movementCalculator.setGameMap(new GameMap(20, 20));
    }

    @ParameterizedTest
    @MethodSource("provideTilesOutsideTheMap")
    public void movingAnUnitOutsideTheGameMap_theMoveIsRefused(final Point from, final Point to)
    {
        MatcherAssert.assertThat(movementCalculator.isValidMove(from, to), is(false));
    }

    static Stream<Arguments> provideTilesOutsideTheMap()
    {
        return Stream.of(Arguments.of(new Point(0, 0), new Point(-1, 0)),
                         Arguments.of(new Point(0, 0), new Point(0, -1)),
                         Arguments.of(new Point(19, 19), new Point(20, 19)),
                         Arguments.of(new Point(19, 19), new Point(19, 20)));
    }
}
