package com.zetcode.game_tiles;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends Tile
{
	ImageIcon iicon_brick_wall = new ImageIcon("src/resources/brick_wall.png");
	ImageIcon iicon_ice_wall = new ImageIcon("src/resources/ice_wall.png");
    ImageIcon iicon_stone_wall = new ImageIcon("src/resources/stone_wall.png");
    ImageIcon iicon_wood_wall = new ImageIcon("src/resources/wood_wall.png");
	
	private Image image;
    
    public Wall(int x, int y)
    {
        super(x, y);
        
        initWall();
    }
    
    private void initWall()
    {
        image = iicon_brick_wall.getImage();
        setImage(image);
    }
    
    public void changeWallBrick()
    {
        image = iicon_brick_wall.getImage();
        setImage(image);
    }
    
    public void changeWallIce()
    {
        image = iicon_ice_wall.getImage();
        setImage(image);
    }
    
    public void changeWallStone()
    {
        image = iicon_stone_wall.getImage();
        setImage(image);
    }
    
    public void changeWallWood()
    {
        image = iicon_wood_wall.getImage();
        setImage(image);
    }
}
