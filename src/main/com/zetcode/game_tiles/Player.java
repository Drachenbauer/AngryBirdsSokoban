package main.com.zetcode.game_tiles;

import javax.swing.ImageIcon;

public class Player extends Movable
{
	public ImageIcon iicon_bird_left;
	public ImageIcon iicon_bird_right;
	public ImageIcon iicon_bird_up;
	public ImageIcon iicon_bird_down;
	
	public ImageIcon iicon_bird_hop_left;
	public ImageIcon iicon_bird_hop_right;
	public ImageIcon iicon_bird_hop_up;
	public ImageIcon iicon_bird_hop_down;
	
    public Player(int x, int y)
    {
        super(x, y);
    }
    
    public void changeBird (String bird)
    {
    	if (bird.equals("The Blues"))
        {
    		iicon_bird_left = new ImageIcon("src/main/resources/birds/The_Blues/bird_left.png");
        	iicon_bird_right = new ImageIcon("src/main/resources/birds/The_Blues/bird_right.png");
        	iicon_bird_up = new ImageIcon("src/main/resources/birds/The_Blues/bird_up.png");
        	iicon_bird_down = new ImageIcon("src/main/resources/birds/The_Blues/bird_down.png");
        	
        	iicon_bird_hop_left = new ImageIcon("src/main/resources/birds/The_Blues/bird_hop_left.png");
        	iicon_bird_hop_right = new ImageIcon("src/main/resources/birds/The_Blues/bird_hop_right.png");
        	iicon_bird_hop_up = new ImageIcon("src/main/resources/birds/The_Blues/bird_hop_up.png");
        	iicon_bird_hop_down = new ImageIcon("src/main/resources/birds/The_Blues/bird_hop_down.png");
        }
        else if (bird.equals("Ice Bird"))
        {
        	iicon_bird_left = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_left.png");
        	iicon_bird_right = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_right.png");
        	iicon_bird_up = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_up.png");
        	iicon_bird_down = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_down.png");
        	
        	iicon_bird_hop_left = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_hop_left.png");
        	iicon_bird_hop_right = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_hop_right.png");
        	iicon_bird_hop_up = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_hop_up.png");
        	iicon_bird_hop_down = new ImageIcon("src/main/resources/birds/Ice_Bird/bird_hop_down.png");
        }
        else
        {
        	iicon_bird_left = new ImageIcon("src/main/resources/birds/" + bird + "/bird_left.png");
        	iicon_bird_right = new ImageIcon("src/main/resources/birds/" + bird + "/bird_right.png");
        	iicon_bird_up = new ImageIcon("src/main/resources/birds/" + bird + "/bird_up.png");
        	iicon_bird_down = new ImageIcon("src/main/resources/birds/" + bird + "/bird_down.png");
        	
        	iicon_bird_hop_left = new ImageIcon("src/main/resources/birds/" + bird + "/bird_hop_left.png");
        	iicon_bird_hop_right = new ImageIcon("src/main/resources/birds/" + bird + "/bird_hop_right.png");
        	iicon_bird_hop_up = new ImageIcon("src/main/resources/birds/" + bird + "/bird_hop_up.png");
        	iicon_bird_hop_down = new ImageIcon("src/main/resources/birds/" + bird + "/bird_hop_down.png");
        }
    }
    
    @Override
    public void undoMove()
    {
    	pos = moves.get(moves.size() - 1);
    	
    	if (pos[0] > x)
    	{
    		setImage(iicon_bird_left);
    	}
    	
    	if (pos[0] < x)
    	{
    		setImage(iicon_bird_right);
    	}
    	
    	if (pos[1] > y)
    	{
    		setImage(iicon_bird_up);
    	}
    	
    	if (pos[1] < y)
    	{
    		setImage(iicon_bird_down);
    	}
    	
    	super.undoMove();
    }
}
