package com.zetcode.game_tiles;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Nest extends Tile
{
    public Nest(int x, int y)
    {
        super(x, y);
        
        initNest();
    }
    
    private void initNest()
    {
        ImageIcon iicon = new ImageIcon("src/resources/nest.png");
        Image image = iicon.getImage();
        setImage(image);
    }
}
