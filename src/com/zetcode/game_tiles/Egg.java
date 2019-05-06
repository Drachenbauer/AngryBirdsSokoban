package com.zetcode.game_tiles;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Egg extends Movable
{
	ImageIcon iicon_egg_1 = new ImageIcon("src/resources/egg_1.png");
	ImageIcon iicon_egg_2 = new ImageIcon("src/resources/egg_2.png");
	
    public Egg(int x, int y)
    {
        super(x, y);
        
        initEgg();
    }
    
    private void initEgg()
    {
        Image image = iicon_egg_1.getImage();
        setImage(image);
    }
    
    public void setImage1()
    {
    	Image image = iicon_egg_1.getImage();
        setImage(image);
    }
    
    public void setImage2()
    {
    	Image image = iicon_egg_2.getImage();
        setImage(image);
    }
}
