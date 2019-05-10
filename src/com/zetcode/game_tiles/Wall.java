package com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Wall extends Tile
{
	public ImageIcon iicon_brick_wall_1 = new ImageIcon("src/resources/brick_wall_1.png");
	public ImageIcon iicon_ice_wall_1 = new ImageIcon("src/resources/ice_wall_1.png");
    public ImageIcon iicon_stone_wall_1 = new ImageIcon("src/resources/stone_wall_1.png");
    public ImageIcon iicon_wood_wall_1 = new ImageIcon("src/resources/wood_wall_1.png");
    public ImageIcon iicon_brick_wall_2 = new ImageIcon("src/resources/brick_wall_2.png");
	public ImageIcon iicon_ice_wall_2 = new ImageIcon("src/resources/ice_wall_2.png");
    public ImageIcon iicon_stone_wall_2 = new ImageIcon("src/resources/stone_wall_2.png");
    public ImageIcon iicon_wood_wall_2 = new ImageIcon("src/resources/wood_wall_2.png");
	
	public Wall(int x, int y)
    {
        super(x, y);
        
        setImage(iicon_brick_wall_1);
    }
}
