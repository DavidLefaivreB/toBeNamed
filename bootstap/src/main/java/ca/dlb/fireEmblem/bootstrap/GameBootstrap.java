package ca.dlb.fireEmblem.bootstrap;

import ca.dlb.fireEmblem.map.AccessibleTilesProvider;
import ca.dlb.fireEmblem.map.GameMap;
import ca.dlb.fireEmblem.map.LevelLoader;

public class GameBootstrap
{
    private final AccessibleTilesProvider accessibleTilesProvider = new AccessibleTilesProvider();

    private final GameMap gameMap;

    public GameBootstrap()
    {
        gameMap = new LevelLoader().loadLevelOne();
        accessibleTilesProvider.setGameMap(gameMap);
    }

    public AccessibleTilesProvider getAccessibleTilesProvider()
    {
        return accessibleTilesProvider;
    }

    public GameMap getGameMap()
    {
        return gameMap;
    }


}
