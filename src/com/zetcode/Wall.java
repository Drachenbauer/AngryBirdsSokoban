package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends Tile
{
	ImageIcon iicon1 = new ImageIcon("src/resources/brick_wall.png");
	ImageIcon iicon2 = new ImageIcon("src/resources/ice_wall.png");
    ImageIcon iicon3 = new ImageIcon("src/resources/stone_wall.png");
    ImageIcon iicon4 = new ImageIcon("src/resources/wood_wall.png");
	
	private Image image;
    
    public Wall(int x, int y)
    {
        super(x, y);
        
        initWall();
    }
    
    private void initWall()
    {
        image = iicon1.getImage();
        setImage(image);
    }
    
    public void changeWallBrick()
    {
        image = iicon1.getImage();
        setImage(image);
    }
    
    public void changeWallIce()
    {
        image = iicon2.getImage();
        setImage(image);
    }
    
    public void changeWallStone()
    {
        image = iicon3.getImage();
        setImage(image);
    }
    
    public void changeWallWood()
    {
        image = iicon4.getImage();
        setImage(image);
    }
}
