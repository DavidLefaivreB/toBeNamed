package com.dlb.fireEmblem.unit;

import com.dlb.fireEmblem.exception.InvalidTileException;
import com.dlb.fireEmblem.map.GameMap;
import com.dlb.fireEmblem.map.model.Tile;
import com.dlb.fireEmblem.map.model.WaterTile;

import java.awt.*;

public class UnitMovementCalculator
{
    private final static int MOVEMENT = 5;

    private GameMap gameMap;

    public void setGameMap(final GameMap gameMap)
    {
        this.gameMap = gameMap;
    }

    public boolean isValidMove(final Point from, final Point to)
    {
        boolean isValid = Math.abs(from.x - to.x) + Math.abs(from.y - to.y) <= MOVEMENT;

        try
        {
            final Tile tile = gameMap.getTileAt(to);

            isValid &= !(tile instanceof WaterTile);
        }
        catch (final InvalidTileException e)
        {
            isValid = false;
        }

        return isValid;
    }
}
