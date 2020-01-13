package ca.dlb.fireEmblem.unit;

import java.awt.*;

public class Unit
{
    private final UnitStats stats;

    private Point position;

    public bUnit(UnitStats stats, Point position)
    {
        this.stats = stats;
        this.position = position;
    }

    public Point getPosition()
    {
        return position;
    }
}
