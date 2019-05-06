package com.zetcode.game_tiles;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Movable
{
	ImageIcon iicon_bird_left = new ImageIcon("src/resources/bird_left.png");
	ImageIcon iicon_bird_right = new ImageIcon("src/resources/bird_right.png");
	ImageIcon iicon_bird_up = new ImageIcon("src/resources/bird_up.png");
	ImageIcon iicon_bird_down = new ImageIcon("src/resources/bird_down.png");
	
	ImageIcon iicon_bird_hop_left = new ImageIcon("src/resources/bird_hop_left.png");
	ImageIcon iicon_bird_hop_right = new ImageIcon("src/resources/bird_hop_right.png");
	ImageIcon iicon_bird_hop_up = new ImageIcon("src/resources/bird_hop_up.png");
	ImageIcon iicon_bird_hop_down = new ImageIcon("src/resources/bird_hop_down.png");
	
    public Player(int x, int y)
    {
        super(x, y);

        initPlayer();
    }

    private void initPlayer()
    {
        Image image = iicon_bird_down.getImage();
        setImage(image);
    }

    public void turnLeft()
    {
    	Image image = iicon_bird_left.getImage();
        setImage(image);
    }
    
    public void turnRight()
    {
    	Image image = iicon_bird_right.getImage();
        setImage(image);
    }
    
    public void turnUp()
    {
    	Image image = iicon_bird_up.getImage();
        setImage(image);
    }
    
    public void turnDown()
    {
    	Image image = iicon_bird_down.getImage();
        setImage(image);
    }
    
    public void hopLeft()
    {
    	Image image = iicon_bird_hop_left.getImage();
        setImage(image);
    }
    
    public void hopRight()
    {
    	Image image = iicon_bird_hop_right.getImage();
        setImage(image);
    }
    
    public void hopUp()
    {
    	Image image = iicon_bird_hop_up.getImage();
        setImage(image);
    }
    
    public void hopDown()
    {
    	Image image = iicon_bird_hop_down.getImage();
        setImage(image);
    }
}
