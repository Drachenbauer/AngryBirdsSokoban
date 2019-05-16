package com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Wall extends Tile
{
	public ImageIcon iicon_wall = new ImageIcon("src/resources/Bricks.png");
	
	public Wall(int x, int y)
    {
        super(x, y);
        
        setImage(iicon_wall);
    }
	
	public void changeWall (String wall)
	{
		iicon_wall = new ImageIcon("src/resources/" + wall + ".png");
		setImage(iicon_wall);
	}
}
