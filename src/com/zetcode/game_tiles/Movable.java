package com.zetcode.game_tiles;

public class Movable extends Tile
{
	private int old_x;
	private int old_y;
	
	private int dx;
	private int dy;
	
	private final int SPACE = 32;
	
	public boolean isMoved = false;
	
	public Movable(int x, int y)
	{
		super(x, y);
		dx = x;
		dy = y;
	}
    
	public void move(int x, int y)
	{
	    dx = this.x + x;
	    dy = this.y + y;
	        
	    setX(dx);
	    setY(dy);
	}
	
	public void savePos()
	{
		isMoved = true;
		old_x = dx;
		old_y = dy;
	}
	
	public void undoStep ()
	{
	    setX(old_x);
	    setY(old_y);
	}
	
	public boolean isLeftCollision(Tile tile)
	{    
	    return x - SPACE == tile.x && y == tile.y;
	}
    
	public boolean isRightCollision(Tile tile)
	{    
	    return x + SPACE == tile.x && y == tile.y;
	}
    
	public boolean isTopCollision(Tile tile)
	{    
	    return x == tile.x && y - SPACE == tile.y;
	}
    
	public boolean isBottomCollision(Tile tile)
	{    
	    return x == tile.x && y + SPACE == tile.y;
	}
}
