package main.com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Egg extends Movable
{
	public ImageIcon iicon_egg_1 = new ImageIcon("src/main/resources/egg_1.png");
	public ImageIcon iicon_egg_2 = new ImageIcon("src/main/resources/egg_2.png");
	
    public Egg(int x, int y)
    {
        super(x, y);
        
        setImage(iicon_egg_1);
    }
}
