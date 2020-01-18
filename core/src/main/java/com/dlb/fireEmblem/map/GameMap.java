package com.dlb.fireEmblem.map;

import com.dlb.fireEmblem.exception.InvalidTileException;
import com.dlb.fireEmblem.map.model.Tile;

import java.awt.*;

public class GameMap
{
    private final Tile[][] tiles;

    public GameMap(final int nbColumns, final int nbRows)
    {
        tiles = new Tile[nbColumns][nbRows];
    }

    public void addTile(final int x, final int y, final Tile tile)
    {
        tiles[x][y] = tile;
    }

    public Tile[][] getMap()
    {
        return tiles;
    }

    public Tile getTileAt(final Point position) throws InvalidTileException
    {
        try
        {
            return tiles[position.x][position.y];
        }
        catch (final IndexOutOfBoundsException e)
        {
            throw new InvalidTileException(String.format("Could not find a tile at position [%s, %s]", position.x, position.y));
        }
    }
}
