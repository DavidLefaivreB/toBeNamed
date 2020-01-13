package ca.dlb.fireEmblem.map;

import ca.dlb.fireEmblem.map.model.Tile;

public class GameMap
{
    private final Tile[][] tiles;

    public GameMap(int nbColumns, int nbRows)
    {
        tiles = new Tile[nbColumns][nbRows];
    }

    public void addTile(int x, int y, Tile tile)
    {
        tiles[x][y] = tile;
    }

    public Tile[][] getMap()
    {
        return tiles;
    }

    public boolean canGoTo(int x, int y)
    {
        try
        {
            return tiles[x][y] != null;
        }
        catch (IndexOutOfBoundsException e)
        {
            return false;
        }
    }
}
