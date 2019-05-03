package com.zetcode.game_tiles;

public class Movable extends Tile
{
	private int old_x;
	private int old_y;
	
	private int dx;
	private int dy;
	
	public boolean moved = false;
	
	public Movable(int x, int y)
	{
		super(x, y);
		dx = x;
		dy = y;
	}
    
	public void move(int x, int y)
	{
		
		
	    dx = x() + x;
	    dy = y() + y;
	        
	    setX(dx);
	    setY(dy);
	    
	    moved = true;
	}
	
	public void savePos()
	{
		old_x = dx;
		old_y = dy;
	}
	
	public void undoStep ()
	{
	    setX(old_x);
	    setY(old_y);
	}
}
