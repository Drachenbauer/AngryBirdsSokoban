package com.zetcode.game_tiles;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile
{
    private int x;
    private int y;
    public Image image;

    public Tile(int x, int y)
    {    
        this.x = x;
        this.y = y;
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
    
    public void setImage(ImageIcon iicon)
    {
        image = iicon.getImage();
    }
}
