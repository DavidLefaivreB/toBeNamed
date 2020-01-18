package com.dlb.fireEmblem.map;

import com.dlb.fireEmblem.map.model.GroundTile;

public class LevelLoader
{
    public GameMap loadLevelOne()
    {
        final GameMap gameMap = new GameMap(15, 15);

        for (int i = 0; i != 14; ++i)
            for (int j = 0; j != 13; ++j)
                gameMap.addTile(i, j, new GroundTile());

        return gameMap;
    }
}
