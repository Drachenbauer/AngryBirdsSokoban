package com.zetcode;

public class Movable extends Tile
{
	public Movable(int x, int y)
	{
		super(x, y);
		// TODO Auto-generated constructor stub
	}
    
	public void move(int x, int y)
	{
	    int dx = x() + x;
	    int dy = y() + y;
	        
	    setX(dx);
	    setY(dy);
	}
}
