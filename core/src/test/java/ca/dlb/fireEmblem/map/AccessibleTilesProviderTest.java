package ca.dlb.fireEmblem.map;

import ca.dlb.fireEmblem.map.model.MapTile;
import ca.dlb.fireEmblem.unit.Unit;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class AccessibleTilesProviderTest
{
    private static final Point CENTER_MAP = new Point(7, 7);
    private static final Point UPPER_LEFT = new Point(0, 0);
    private static final Point LOWER_RIGHT = new Point(14, 14);

    private static final List<Point> CENTER_UNIT_COMBINATIONS = Arrays.asList(new Point(6, 7), new Point(7, 6), new Point(8, 7), new Point(7, 8));
    private static final List<Point> UPPER_LEFT_UNIT_COMBINATIONS = Arrays.asList(new Point(1, 0), new Point(0, 1));
    private static final List<Point> LOWER_RIGHT_UNIT_COMBINATIONS = Arrays.asList(new Point(13, 14), new Point(14, 13));
    private static final List<Point> TWO_MOVE_COMBINATIONS = Arrays.asList(new Point(6, 7), new Point (6, 6), new Point(7, 6), new Point(8, 6), new Point(8, 7), new Point(8, 8), new Point(7, 8), new Point(6, 8));

    AccessibleTilesProvider accessibleTilesProvider = new AccessibleTilesProvider();

    @Test
    public void givenUnitIsOnCenterOfMap_whenRetrievingAccessibleTiles_allTilesAreFound()
    {
        GameMap gameMap = getGameMap();
        accessibleTilesProvider.setGameMap(gameMap);
        Unit centerUnit = createUnit(CENTER_MAP);

        List<Point> actualCombinations = accessibleTilesProvider.getAccessibleTiles(centerUnit);

        isContainsSameCombinations(CENTER_UNIT_COMBINATIONS, actualCombinations);
    }

    @Test
    public void givenUnitIsOnUpperLeftBorderOfMap_whenRetrievingAccessibleTiles_onlyTilesInsideTheMapAreFound()
    {
        GameMap gameMap = getGameMap();
        accessibleTilesProvider.setGameMap(gameMap);
        Unit upperLeftUnit = createUnit(UPPER_LEFT);

        List<Point> actualCombinations = accessibleTilesProvider.getAccessibleTiles(upperLeftUnit);

        isContainsSameCombinations(UPPER_LEFT_UNIT_COMBINATIONS, actualCombinations);
    }

    @Test
    public void givenUnitIsOnLowerRightBorderOfMap_whenRetrievingAccessibleTiles_onlyTilesInsideTheMapAreFound()
    {
        GameMap gameMap = getGameMap();
        accessibleTilesProvider.setGameMap(gameMap);
        Unit lowerRightUnit = createUnit(LOWER_RIGHT);

        List<Point> actualCombinations = accessibleTilesProvider.getAccessibleTiles(lowerRightUnit);

        isContainsSameCombinations(LOWER_RIGHT_UNIT_COMBINATIONS, actualCombinations);
    }

    @Test
    public void givenUnitCanMoveUpToTwoTiles_whenRetrievingAccessibleTiles_allTilesAreFound()
    {
        GameMap gameMap = getGameMap();
        accessibleTilesProvider.setGameMap(gameMap);
        Unit centerUnit = createUnit(CENTER_MAP);

        List<Point> actualCombinations = accessibleTilesProvider.getAccessibleTiles(centerUnit);

        isContainsSameCombinations(TWO_MOVE_COMBINATIONS, actualCombinations);
    }

    private Unit createUnit(Point position)
    {
        return new Unit(() -> 1, position);
    }

    private void isContainsSameCombinations(List<Point> expected, List<Point> actual)
    {
        assertThat(actual.size(), is(equalTo(expected.size())));

        for (Point point : actual)
        {
            assertThat(expected, hasItem(point));
        }
    }

    public GameMap getGameMap()
    {
        GameMap gameMap = new GameMap(15, 15);

        for (int i = 0; i != 15; ++i)
        {
            for (int j = 0; j != 15; ++j)
            {
                gameMap.addTile(i, j, new MapTile());
            }
        }

        return gameMap;
    }
}
