package com.zetcode.game_tiles;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Nest extends Tile
{
	ImageIcon iicon_nest = new ImageIcon("src/resources/nest.png");
	
    public Nest(int x, int y)
    {
        super(x, y);
        
        initNest();
    }
    
    private void initNest()
    {
        
        Image image = iicon_nest.getImage();
        setImage(image);
    }
}
