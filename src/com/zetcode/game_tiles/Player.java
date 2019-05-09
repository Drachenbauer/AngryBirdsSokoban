package com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Player extends Movable
{
	public ImageIcon iicon_bird_left = new ImageIcon("src/resources/bird_left.png");
	public ImageIcon iicon_bird_right = new ImageIcon("src/resources/bird_right.png");
	public ImageIcon iicon_bird_up = new ImageIcon("src/resources/bird_up.png");
	public ImageIcon iicon_bird_down = new ImageIcon("src/resources/bird_down.png");
	
	public ImageIcon iicon_bird_hop_left = new ImageIcon("src/resources/bird_hop_left.png");
	public ImageIcon iicon_bird_hop_right = new ImageIcon("src/resources/bird_hop_right.png");
	public ImageIcon iicon_bird_hop_up = new ImageIcon("src/resources/bird_hop_up.png");
	public ImageIcon iicon_bird_hop_down = new ImageIcon("src/resources/bird_hop_down.png");
	
    public Player(int x, int y)
    {
        super(x, y);

        setImage(iicon_bird_down);
    }
}
