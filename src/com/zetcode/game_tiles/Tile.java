package com.zetcode.game_tiles;

import java.awt.Image;

public class Tile
{
    private final int SPACE = 32;

    private int x;
    private int y;
    private Image image;

    public Tile(int x, int y)
    {    
        this.x = x;
        this.y = y;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image img)
    {
        image = img;
    }

    public int x()
    {    
        return x;
    }

    public int y()
    {    
        return y;
    }

    public void setX(int x)
    {    
        this.x = x;
    }

    public void setY(int y)
    {    
        this.y = y;
    }

    public boolean isLeftCollision(Tile actor)
    {    
        return x() - SPACE == actor.x() && y() == actor.y();
    }

    public boolean isRightCollision(Tile actor)
    {    
        return x() + SPACE == actor.x() && y() == actor.y();
    }

    public boolean isTopCollision(Tile actor)
    {    
        return y() - SPACE == actor.y() && x() == actor.x();
    }

    public boolean isBottomCollision(Tile actor)
    {    
        return y() + SPACE == actor.y() && x() == actor.x();
    }
}
