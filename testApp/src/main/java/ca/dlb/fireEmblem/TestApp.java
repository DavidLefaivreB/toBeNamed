package ca.dlb.fireEmblem;

import ca.dlb.fireEmblem.bootstrap.GameBootstrap;
import ca.dlb.fireEmblem.map.model.Tile;
import ca.dlb.fireEmblem.unit.Archer;
import ca.dlb.fireEmblem.unit.Unit;

import java.awt.*;
import java.util.List;

public class TestApp
{
    public void run()
    {
        GameBootstrap gameBootstrap = new GameBootstrap();
        Unit unit = new Unit(new Archer(), new Point(7, 7));

        Tile[][] tiles = gameBootstrap.getGameMap().getMap();

        List<Point> validMoves = gameBootstrap.getAccessibleTilesProvider().getAccessibleTiles(unit);

        for (int i = 0; i != tiles.length; ++i)
        {
            for (int j = 0; j != tiles[i].length; ++j)
            {
                displayTile(i, j, unit, validMoves);
            }
            System.out.println("");
        }
    }

    private void displayTile(int x, int y, Unit unit, List<Point> validMoves)
    {
        if (isUnitOnTile(x, y, unit))
            displayUnitTile();
        else if (validMoves.contains(new Point(x, y)))
            displayValidMoveTile();
        else
            displayEmptyTile();
    }

    private void displayUnitTile()
    {
        System.out.print("[1]");
    }

    private void displayValidMoveTile()
    {
        System.out.print("[ ]");
    }

    private void displayEmptyTile()
    {
        System.out.print("[X]");
    }

    private boolean isUnitOnTile(int x, int y, Unit unit)
    {
        return x == unit.getPosition().x && y == unit.getPosition().y;
    }
}
