package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Egg extends Actor
{
    public Egg(int x, int y)
    {
        super(x, y);
        
        initEgg();
    }
    
    private void initEgg()
    {
        ImageIcon iicon = new ImageIcon("src/resources/egg.png");
        Image image = iicon.getImage();
        setImage(image);
    }
    
    public void move(int x, int y)
    {
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
