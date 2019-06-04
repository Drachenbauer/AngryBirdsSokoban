package main.com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Nest extends Tile
{
    ImageIcon iicon_nest = new ImageIcon("src/main/resources/nest.png");
    
    public Nest(int x, int y)
    {
        super(x, y);
        
        setImage(iicon_nest);
    }
}
