package ca.dlb.fireEmblem.map;

import ca.dlb.fireEmblem.unit.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccessibleTilesProvider
{
    private GameMap gameMap;

    public List<Point> getAccessibleTiles(Unit unit)
    {
        List<Point> accessibleTiles = new ArrayList<>();
        Point position = unit.getPosition();

        if (gameMap.canGoTo(position.x - 1, position.y))
            accessibleTiles.add(new Point(position.x - 1, position.y));

        if (gameMap.canGoTo(position.x, position.y - 1))
            accessibleTiles.add(new Point(position.x, position.y - 1));

        if (gameMap.canGoTo(position.x + 1, position.y))
            accessibleTiles.add(new Point(position.x + 1, position.y));

        if (gameMap.canGoTo(position.x, position.y + 1))
            accessibleTiles.add(new Point(position.x, position.y + 1));

        return accessibleTiles;
    }

    public void setGameMap(GameMap gameMap)
    {
        this.gameMap = gameMap;
    }
}
