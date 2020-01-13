package ca.dlb.fireEmblem.map;

import ca.dlb.fireEmblem.map.model.MapTile;

public class LevelLoader
{
    public GameMap loadLevelOne()
    {
        GameMap gameMap = new GameMap(15, 15);

        for (int i = 0; i != 14; ++i)
        {
            for (int j = 0; j != 13; ++j)
            {
                gameMap.addTile(i , j, new MapTile());
            }
        }

        return gameMap;
    }
}
