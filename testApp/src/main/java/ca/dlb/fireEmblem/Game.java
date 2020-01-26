package ca.dlb.fireEmblem;

import com.dlb.fireEmblem.map.model.ForestTile;
import com.dlb.fireEmblem.map.model.GroundTile;
import com.dlb.fireEmblem.map.model.Tile;
import com.dlb.fireEmblem.map.model.WaterTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private static final int MOVEMENT = 7;

    void start()
    {
        final GameMap map = createMap();

        final Tile[][] tiles = map.getTiles();

        map.addTile(2, 0, new WaterTile());
        map.addTile(1, 0, new ForestTile());
        map.addTile(0, 7, new ForestTile());

        final Unit unit = new PegasusFighter(0, 0);

        final List<Point> accessibleTiles = getAccessibleTiles(unit, unit.getPosition(), map.getTiles(), MOVEMENT);

        for (int y = 0; y != 10; ++y)
        {
            for (int x = 0; x != 10; ++x)
            {
                final Tile tile = tiles[x][y];

                if (x == unit.getPosition().x && y == unit.getPosition().y)
                {
                    System.out.print("[X]");
                }
                else if (accessibleTiles.contains(new Point(x, y)))
                {
                    System.out.print("[ ]");
                }
                else if (tile instanceof GroundTile)
                {
                    System.out.print("[G]");
                }
                else if (tile instanceof WaterTile)
                {
                    System.out.print("[W]");
                }
                else if (tile instanceof ForestTile)
                {
                    System.out.print("[F]");
                }
                else
                {
                    throw new NullPointerException("I am lazy");
                }
            }

            System.out.println();
        }
    }

    private List<Point> getAccessibleTiles(final Unit unit, final Point position, final Tile[][] tiles, final int nbTilesLeft)
    {
        final List<Point> accessibleTiles = new ArrayList<>();

        if (nbTilesLeft == 0)
            return new ArrayList<>();

        if (position.x - 1 > -1)
            addTileIfAccessible(unit, tiles, nbTilesLeft, accessibleTiles, new Point(position.x - 1, position.y));

        if (position.y - 1 > -1)
            addTileIfAccessible(unit, tiles, nbTilesLeft, accessibleTiles, new Point(position.x, position.y - 1));

        if (position.x + 1 < 9)
            addTileIfAccessible(unit, tiles, nbTilesLeft, accessibleTiles, new Point(position.x + 1, position.y));

        if (position.y + 1 < 9)
            addTileIfAccessible(unit, tiles, nbTilesLeft, accessibleTiles, new Point(position.x, position.y + 1));

        return accessibleTiles;
    }

    private void addTileIfAccessible(final Unit unit, final Tile[][] tiles, int nbTilesLeft, final List<Point> accessibleTiles, final Point destination)
    {
        if (isValidTile(unit, tiles[destination.x][destination.y], nbTilesLeft))
        {
            if (tiles[destination.x][destination.y] instanceof ForestTile)
                --nbTilesLeft;

            accessibleTiles.add(destination);
            accessibleTiles.addAll(getAccessibleTiles(unit, destination, tiles, nbTilesLeft - 1));
        }
    }

    private boolean isValidTile(final Unit unit, final Tile tile, final int nbTilesLeft)
    {
        if (tile instanceof GroundTile || (tile instanceof ForestTile && nbTilesLeft >= 2))
            return true;
        else
            return tile instanceof WaterTile && unit.canWalkOnWater();
    }

    GameMap createMap()
    {
        return new GameMap(10, 10);
    }

    private class GameMap
    {
        Tile[][] tiles;

        GameMap(final int length, final int height)
        {
            tiles = new Tile[length][height];

            for (int y = 0; y != height; ++y)
            {
                for (int x = 0; x != length; ++x)
                {
                    tiles[x][y] = new GroundTile();
                }
            }
        }

        public Tile[][] getTiles()
        {
            return tiles;
        }

        public void addTile(final int x, final int y, final Tile tile)
        {
            tiles[x][y] = tile;
        }
    }

    private interface Unit
    {
        boolean canWalkOnWater();

        Point getPosition();
    }

    private class Archer implements Unit
    {
        Point position;

        public Archer(final int x, final int y)
        {
            position = new Point(x, y);
        }

        @Override
        public Point getPosition()
        {
            return position;
        }

        @Override
        public boolean canWalkOnWater()
        {
            return false;
        }
    }

    private class PegasusFighter implements Unit
    {
        Point position;

        public PegasusFighter(final int x, final int y)
        {
            position = new Point(x, y);
        }

        @Override
        public Point getPosition()
        {
            return position;
        }

        @Override
        public boolean canWalkOnWater()
        {
            return true;
        }
    }
}