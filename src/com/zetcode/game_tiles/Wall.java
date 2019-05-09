package com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Wall extends Tile
{
	public ImageIcon iicon_brick_wall = new ImageIcon("src/resources/brick_wall.png");
	public ImageIcon iicon_ice_wall = new ImageIcon("src/resources/ice_wall.png");
    public ImageIcon iicon_stone_wall = new ImageIcon("src/resources/stone_wall.png");
    public ImageIcon iicon_wood_wall = new ImageIcon("src/resources/wood_wall.png");
	
	public Wall(int x, int y)
    {
        super(x, y);
        
        setImage(iicon_brick_wall);
    }
}
